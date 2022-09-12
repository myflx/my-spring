package com.test.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Aspect
@Component
public class LogAspect {

    @Before("execution(* com.test.service.AddressService..*(..))")
    public void log() {
        System.out.println("before aspect.....");
    }
}
