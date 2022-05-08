package com.myflx.biz;

import com.myflx.spring.annotation.Autowired;
import com.myflx.spring.annotation.Component;
import com.myflx.spring.annotation.Scope;
import com.myflx.spring.bean.InitializingBean;

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
