package com.myflx.processor;

import com.myflx.dao.OrderDao;
import com.myflx.service.OrderService;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        //如果此处返回了一个非空对象，说明应用层接管了Bean的实例化
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof OrderService)
            System.out.println("OrderService postProcessBeforeInitialization");
        //此处返回了空对象，导致BeanPostProcessor的处理链断掉
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof OrderService)
            System.out.println("OrderService postProcessAfterInitialization");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        //控制开关，告诉spring是否有IOC容器进行属性填充
        //false=属性由外界完成
        //对于bean的属性填充可以此处完成
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (bean instanceof OrderService){
            ((OrderService) bean).setTestPopulate("test");
        }
        return pvs;
    }
}
