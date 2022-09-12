package com.test.biz;

import com.test.spring.annotation.Autowired;
import com.test.spring.annotation.Component;
import com.test.spring.bean.InitializingBean;

@Component
public class OrderService implements InitializingBean {
    @Autowired
    private OrderDao orderDao;

    public void hello() {
        System.out.println("OrderService hello...");
        orderDao.hello();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("OrderService afterPropertiesSet...");
    }
}
