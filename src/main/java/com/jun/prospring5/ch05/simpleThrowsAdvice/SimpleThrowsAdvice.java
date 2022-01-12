package com.jun.prospring5.ch05.simpleThrowsAdvice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleThrowsAdvice implements ThrowsAdvice {
    public static void main(String[] args) {
        ErrorBean errorBean = new ErrorBean();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(errorBean);
        pf.addAdvice(new SimpleThrowsAdvice());

        ErrorBean proxy = (ErrorBean) pf.getProxy();

        try {
            proxy.errorProneMethod();
        } catch (Exception ignored) {
        }

        try {
            proxy.otherErrorProneMethod();
        } catch (Exception ignored) {
        }


    }

    public void afterThrowing(Exception e) throws Throwable{
        System.out.println("***");
        System.out.println("Generic Exception Capture");
        System.out.println("Caught: " + e.getClass().getName());
        System.out.println("***\n");
    }

    public void afterThrowing(Method method, Object args, Object target, IllegalArgumentException e) {
        System.out.println("***");
        System.out.println("IllegalArgumentException Capture");
        System.out.println("Caught: " + e.getClass().getName());
        System.out.println("Method: " + method.getName());
        System.out.println("***\n");
    }

}
