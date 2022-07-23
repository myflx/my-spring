package com.myflx.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(invocation.getMethod() + "开始执行。。。");
        Object proceed = invocation.proceed();
        System.out.println(invocation.getMethod() + "执行结果：" + proceed);
        return proceed;
    }
}
