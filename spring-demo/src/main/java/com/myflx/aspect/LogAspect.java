package com.myflx.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Aspect
@Component
public class LogAspect {

    @Before("execution(* com.myflx.dao.OrderDao..*(..))")
    public void log() {
        System.out.println("before aspect.....");
    }
}
