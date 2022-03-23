package com.myflx.service;

import com.myflx.dao.OrderDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
//@Component
public class OrderService implements InitializingBean, BeanFactoryAware {
    public OrderService() {
        System.out.println("OrderService construct....");
    }

    @Autowired
    private OrderDao orderDao;

    public void hello() {
        System.out.println("orderService hello..");
        orderDao.hello();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("OrderService initializing1...");
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("OrderService initializing000...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("是不是AutowireCapableBeanFactory:" + (beanFactory instanceof AutowireCapableBeanFactory));
    }
}
