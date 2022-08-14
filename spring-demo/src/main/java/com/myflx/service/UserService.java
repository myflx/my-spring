package com.myflx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AddressService addressService;

    public void hello() {
        System.out.println("UserService hello...");
    }

    /*@Async
    public void asyncHello() {
        System.out.println("UserService async hello...");
    }*/
}
