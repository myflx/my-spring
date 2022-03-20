package com.myflx.spring.bean;

import lombok.Data;

@Data
public class BeanDefinition {
    String beanName;
    Class<?> classType;
    String scope;
}
