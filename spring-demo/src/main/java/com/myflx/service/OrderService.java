package com.myflx.service;

import com.myflx.dao.OrderDao;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Component
public class OrderService implements InitializingBean, DisposableBean, BeanFactoryAware {
    public OrderService() {
        System.out.println("OrderService construct....");
    }

    @Setter
    private String testPopulate;

    @Autowired
    private OrderDao orderDao;

    public void hello() {
        System.out.println("orderService hello.." + testPopulate);
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

    @PostConstruct
    public void init2() throws Exception {
        System.out.println("OrderService initializing001...");
    }

    @PostConstruct
    public void init3() throws Exception {
        System.out.println("OrderService initializing002...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("是不是AutowireCapableBeanFactory:" + (beanFactory instanceof AutowireCapableBeanFactory));
    }

    @PreDestroy
    @Override
    public void destroy() throws Exception {
        System.out.println("OrderService destroy...");
    }
}
