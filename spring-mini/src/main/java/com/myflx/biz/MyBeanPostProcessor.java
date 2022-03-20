package com.myflx.biz;

import com.myflx.spring.annotation.Component;
import com.myflx.spring.bean.BeanPostProcessor;

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
