package com.test.biz;

import com.test.spring.annotation.Autowired;
import com.test.spring.annotation.Component;

@Component
public class OrderDao {
    @Autowired
    OrderService orderService;
    public void hello() {
        System.out.println("OrderDao hello...");
    }
}
