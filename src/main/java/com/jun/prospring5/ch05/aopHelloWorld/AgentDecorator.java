package com.jun.prospring5.ch05.aopHelloWorld;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AgentDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("James ");

        Object retVal = invocation.proceed();
        System.out.println("!");

        return retVal;
    }
}
