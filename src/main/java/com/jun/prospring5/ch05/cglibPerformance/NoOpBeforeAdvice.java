package com.jun.prospring5.ch05.cglibPerformance;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class NoOpBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // no op
    }
}
