package com.jun.prospring5.ch05.cglibPerformance;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class TestPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return ("advised".equals(method.getName()));
    }
}
