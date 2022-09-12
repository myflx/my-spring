package com.test.spring.bean;

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
