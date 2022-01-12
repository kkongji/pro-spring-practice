package com.jun.prospring5.ch05.simpleAroundAdvice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(invocation.getMethod().getName());
        Object returnValue = invocation.proceed();

        sw.stop();

        dumpInfo(invocation, sw.getTotalTimeMillis());
        return returnValue;
    }

    private void dumpInfo(MethodInvocation invocation, long ms) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();

        System.out.println("실행된 메서드: " + method.getName());
        System.out.println("객체 타입: " + target.getClass().getName());
        System.out.println("인수: ");
        for (int i = 0; i < args.length; i++) {
            System.out.println("> " + args[i]);
        }

        System.out.println("\n");
        System.out.println("실행시간: " + ms + "ms");
    }
}
