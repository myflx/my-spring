package com.test;

import com.test.config.AppConfig;
import com.test.service.AddressService;
import com.test.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Bootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = (OrderService) context.getBean("orderService");
        orderService.hello();

        AddressService addressService = (AddressService) context.getBean("addressService");


        context.registerShutdownHook();
    }
}
