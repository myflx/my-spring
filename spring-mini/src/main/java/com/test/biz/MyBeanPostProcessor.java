package com.test.biz;

import com.test.spring.annotation.Component;
import com.test.spring.bean.BeanPostProcessor;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof OrderService) {
            System.out.println(beanName + ":postProcessBeforeInitialization");
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof OrderService) {
            System.out.println(beanName + ":postProcessBeforeInitialization");
        }
        return null;
    }
}
