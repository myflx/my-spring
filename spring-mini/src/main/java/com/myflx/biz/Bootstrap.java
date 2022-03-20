package com.myflx.biz;

import com.myflx.spring.context.AnnotationConfigApplicationContext;

public class Bootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        orderService.hello();
        System.out.println(orderService == applicationContext.getBean("orderService"));
    }
}
