package com.myflx.advice;

public class TargetBean {
    public String hello() {
        System.out.println("TargetBean hello...");
        return "hello";
    }

    public String hello2() {
        System.out.println("TargetBean hello2...");
        return "hello2";
    }

    private String sing() {
        System.out.println("TargetBean sing...");
        return "sing";
    }

    public String throwNPE() {
        throw new NullPointerException("throwNPE");
    }
    public String throwE() throws Exception {
        throw new Exception("throwE");
    }
}
