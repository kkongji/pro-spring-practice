package com.jun.prospring5.ch05.proxyfactorybean;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AuditAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        System.out.println("Executing: " + method);
    }
}
