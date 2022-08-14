package com.myflx.advice;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

import java.io.Serializable;

public class AopTest {

    @Test
    public void testAdvice() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new TargetBean());
        proxyFactory.addAdvice(new SimpleAroundAdvice());

        TargetBean targetBean = (TargetBean) proxyFactory.getProxy();
        targetBean.hello();
        targetBean.hello2();
        //执行结果。。
//        public java.lang.String com.myflx.advice.TargetBean.hello()开始执行。。。
//        TargetBean hello...
//        public java.lang.String com.myflx.advice.TargetBean.hello()执行结果：hello
    }

    @Test
    public void testThrowsAdvice() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new TargetBean());
        proxyFactory.addAdvice(new SimpleThrowsAdvice());

        TargetBean targetBean = (TargetBean) proxyFactory.getProxy();
        targetBean.throwNPE();
        targetBean.throwE();
        //执行结果。。
    }

    @Test
    public void testAdvisor() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new TargetBean());

        SimpleAroundAdvice simpleAroundAdvice = new SimpleAroundAdvice();
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("hello");

        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(simpleAroundAdvice);
        defaultPointcutAdvisor.setPointcut(pointcut);

        proxyFactory.addAdvisor(defaultPointcutAdvisor);
        TargetBean targetBean = (TargetBean) proxyFactory.getProxy();
        targetBean.hello();
        targetBean.hello2();
    }

    @Test
    public void testAdvisor2() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new TargetBean());

        SimpleAroundAdvice simpleAroundAdvice = new SimpleAroundAdvice();
        NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor = new NameMatchMethodPointcutAdvisor(simpleAroundAdvice);
        nameMatchMethodPointcutAdvisor.addMethodName("hello");

        proxyFactory.addAdvisor(nameMatchMethodPointcutAdvisor);
        TargetBean targetBean = (TargetBean) proxyFactory.getProxy();
        targetBean.hello();
        targetBean.hello2();
    }

    @Test
    public void testAdvisor3() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new TargetBean());

        SimpleAroundAdvice simpleAroundAdvice = new SimpleAroundAdvice();
        AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor =
                new AspectJExpressionPointcutAdvisor();
        aspectJExpressionPointcutAdvisor.setAdvice(simpleAroundAdvice);
        aspectJExpressionPointcutAdvisor.setExpression("execution(* hello2*(..))");
        proxyFactory.addAdvisor(aspectJExpressionPointcutAdvisor);
        TargetBean targetBean = (TargetBean) proxyFactory.getProxy();
        targetBean.hello();
        targetBean.hello2();
    }

    @Test
    public void testTargetClass() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new TargetBean());
        proxyFactory.addAdvice(new SimpleAroundAdvice());
        proxyFactory.addInterface(Serializable.class);
        proxyFactory.setProxyTargetClass(true);
        Object proxy = proxyFactory.getProxy();
        System.out.println(proxy instanceof TargetBean);
        //执行结果。。
//        public java.lang.String com.myflx.advice.TargetBean.hello()开始执行。。。
//        TargetBean hello...
//        public java.lang.String com.myflx.advice.TargetBean.hello()执行结果：hello
    }
}


