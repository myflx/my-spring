package com.test.biz;

import com.test.spring.context.AnnotationConfigApplicationContext;

public class Bootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        orderService.hello();
        System.out.println(orderService == applicationContext.getBean("orderService"));
    }
}
