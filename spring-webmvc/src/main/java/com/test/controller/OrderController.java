package com.test.controller;

import com.test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @PostMapping("/hello")
    public String hello() {
        return "Hello OrderController..--->" + orderService.hello();
    }

    @PostMapping("/hello3")
    public String hello3(RequestSource requestSource) {
        return "Hello OrderController..--->" + orderService.hello();
    }

    @GetMapping("/async")
    public Callable<String> async() {
        StringBuffer stringBuffer = new StringBuffer("initial");
        Callable<String> call = () -> {
            CompletableFuture<Void> success = CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stringBuffer.append("Success");
            });
            success.get();
            return stringBuffer.toString();
        };
        return call;
    }

    @GetMapping("/async2")
    @Async
    public CompletableFuture<?> async2() {
        CompletableFuture<String> success = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Success";
        });
        return success;
    }


    @PostMapping(value = "/testConsumes",consumes = "text/plain",produces = "application/json;charset=utf-8")
    public String testConsumes() {
        return "Hello OrderController..--->" + orderService.hello();
    }

    @PostMapping(value = "/testConsumes",consumes = "application/json",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderInfo testConsumes1() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setName("你好");
        return orderInfo;
    }
}
