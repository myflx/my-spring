package com.myflx.processor;

import com.myflx.dao.OrderDao;
import com.myflx.service.OrderService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof OrderService)
            System.out.println("OrderService postProcessBeforeInitialization");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof OrderService)
            System.out.println("OrderService postProcessAfterInitialization");
        return null;
    }
}
