package com.myflx.spring.bean;

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
