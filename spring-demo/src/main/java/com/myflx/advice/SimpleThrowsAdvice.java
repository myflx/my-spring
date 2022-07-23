package com.myflx.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class SimpleThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception e) {
        System.out.println("出现异常1：" + e);
    }
    public void afterThrowing(NullPointerException e) {
        System.out.println("出现异常2：" + e);
    }

    public void afterThrowing(Method method, Object args, Object target, Exception e) {
        System.out.println("出现异常3：" + e);
    }
    public void afterThrowing(Method method, Object args, Object target, NullPointerException e) {
        System.out.println("出现异常4：" + e);
    }
}
