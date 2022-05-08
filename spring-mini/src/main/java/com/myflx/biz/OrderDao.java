package com.myflx.biz;

import com.myflx.spring.annotation.Autowired;
import com.myflx.spring.annotation.Component;

@Component
public class OrderDao {
    @Autowired
    OrderService orderService;
    public void hello() {
        System.out.println("OrderDao hello...");
    }
}
