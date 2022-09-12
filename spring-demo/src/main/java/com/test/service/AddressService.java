package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
//    @Lazy
    private UserService userService;

    public void hello() {
        System.out.println("AddressService hello...");
    }

    @Async
    public void asyncHello() {
        System.out.println("UserService async hello...");
    }
}
