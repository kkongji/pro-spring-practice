package com.jun.prospring5.ch05.aopNamespace;

import org.aspectj.lang.JoinPoint;

public class SimpleAdvice {
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("Executing: " +
                joinPoint.getSignature().getDeclaringTypeName() + "" +
                joinPoint.getSignature().getName());
    }
}
