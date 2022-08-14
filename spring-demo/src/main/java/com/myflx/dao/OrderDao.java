package com.myflx.dao;

import com.myflx.service.OrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao implements InitializingBean {

    @Autowired
    private OrderService orderService;
    private String init = null;

    public void hello() {
        System.out.println("orderDao hello..");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init = "dsfsd";
    }
}
