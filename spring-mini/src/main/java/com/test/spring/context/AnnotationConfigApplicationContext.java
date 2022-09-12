package com.test.spring.context;

import com.test.spring.annotation.Autowired;
import com.test.spring.annotation.Component;
import com.test.spring.annotation.ComponentScan;
import com.test.spring.annotation.Scope;
import com.test.spring.bean.BeanDefinition;
import com.test.spring.bean.BeanPostProcessor;
import com.test.spring.bean.InitializingBean;
import com.test.spring.bean.ScopeType;
import com.test.spring.exception.BeansException;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationConfigApplicationContext {
    private final Class<?> configClass;
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(16);
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>(16);
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public AnnotationConfigApplicationContext(Class<?> configClass) {
        this.configClass = configClass;
        //1.扫描。扫描包路径、解析所有文件信息。做判断
        //2.注册。如果说是符合条件的Bean（@Component 注解的类），将相关的Bean的信息转换成BeanDefinition
        doScan();
        //3.实例化。单例对象（非懒加载的对象）
        initializeNotLazyBean();
    }
    /**
     * 扫描+注册
     */
    private void doScan() {
        if (!configClass.isAnnotationPresent(ComponentScan.class)) {
            return;
        }
        ComponentScan annotation = configClass.getAnnotation(ComponentScan.class);
        String packageName = annotation.value();//com.myflx
        String filePath = packageName.replaceAll("\\.", "/");//com/myflx
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(filePath);
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                String className = packageName + "." + file1.getName().replaceAll(".class", "");
                Class<?> aClass = null;
                try {
                    aClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                //判断是否符合条件;
                if (aClass.isAnnotationPresent(Component.class)) {
                    Component component = aClass.getAnnotation(Component.class);
                    String beanName;
                    if ("".equals(component.value())) {
                        beanName = Introspector.decapitalize(aClass.getSimpleName());
                    } else {
                        beanName = component.value();
                    }
                    String scope = ScopeType.SINGLETON;
                    if (aClass.isAnnotationPresent(Scope.class)) {
                        scope = aClass.getAnnotation(Scope.class).value();
                        //判断是不是 ScopeType里边
                    }
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setClassType(aClass);
                    beanDefinition.setBeanName(beanName);
                    beanDefinition.setScope(scope);
                    beanDefinitionMap.put(beanName, beanDefinition);
                    if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                        beanPostProcessors.add((BeanPostProcessor) getBean(beanName));
                    }
                }
            }
        }
    }

    /**
     * 实例化-单例对象
     */
    private void initializeNotLazyBean() {
        for (Map.Entry<String, BeanDefinition> definitionEntry : beanDefinitionMap.entrySet()) {
            BeanDefinition definition = definitionEntry.getValue();
            //做判断：单例、非抽象
            if (ScopeType.SINGLETON.equals(definition.getScope())) {
                //创建Bean的流程
                createBean(definition);
            }
        }
    }

    /**
     * 带缓存的Bean创建
     */
    private Object createBean(BeanDefinition definition) {
        String beanName = definition.getBeanName();
        if (singletonObjects.containsKey(beanName)) {
            return singletonObjects.get(beanName);
        }
        //实际创建Bean的过程
        Object bean = doCreateBean(definition);
        //singletonObjects.put(beanName, bean);
        return bean;
    }

    /**
     * 直接创建Bean
     */
    private Object doCreateBean(BeanDefinition definition) {
        Object exposedObject = null;
        try {

            Class<?> classType = definition.getClassType();
            //实例化
            Object instance = classType.getConstructor().newInstance();
            //提前暴露对象
            singletonObjects.put(definition.getBeanName(), instance);
            //属性填充
            populateBean(instance, definition);
            //对象初始化
            exposedObject = initializing(instance, definition);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeansException(definition.getBeanName() + ":创建异常");
        }
        return exposedObject;
    }
    private Object initializing(Object instance, BeanDefinition definition) throws Exception {
        //初始化前置操作
        Object rs = postProcessBeforeInitialization(instance, definition);
        if (rs != null) {
            return rs;
        }
        //实际初始化过程
        if (instance instanceof InitializingBean) {
            ((InitializingBean) instance).afterPropertiesSet();
        }
        //初始化后置操作
        rs = postProcessAfterInitialization(instance, definition);
        if (rs != null) {
            return rs;
        }
        return instance;
    }
    private Object postProcessAfterInitialization(Object instance, BeanDefinition definition) {
        if (instance instanceof BeanPostProcessor) {
            return instance;
        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            Object o = beanPostProcessor.postProcessAfterInitialization(instance, definition.getBeanName());
            if (o != null) return o;
        }
        return null;
    }
    private Object postProcessBeforeInitialization(Object instance, BeanDefinition definition) {
        if (instance instanceof BeanPostProcessor) {
            return instance;
        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            Object o = beanPostProcessor.postProcessBeforeInitialization(instance, definition.getBeanName());
            if (o != null) return o;
        }
        return null;
    }
    private void populateBean(Object instance, BeanDefinition definition) {
        Class<?> classType = definition.getClassType();
        Field[] declaredFields = classType.getDeclaredFields();
        try {
            for (Field declaredField : declaredFields) {
                //做判断。@Autowired注解了
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    String simpleName = declaredField.getType().getSimpleName();
                    String fieldBeanName = Introspector.decapitalize(simpleName);
                    declaredField.setAccessible(true);
                    declaredField.set(instance, getBean(fieldBeanName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeansException(definition.getBeanName() + ":创建异常。。");
        }
    }

    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new BeansException("找不到这样Bean定义。。");
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (ScopeType.SINGLETON.equals(beanDefinition.getScope())) {
            return createBean(beanDefinition);
        } else {
            return doCreateBean(beanDefinition);
        }
    }
}
