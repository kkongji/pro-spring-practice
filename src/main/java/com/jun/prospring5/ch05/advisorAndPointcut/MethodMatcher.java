package com.jun.prospring5.ch05.advisorAndPointcut;

import java.lang.reflect.Method;

public interface MethodMatcher {
    boolean matches(Method m, Class<?> targetClass);

    boolean isRuntime();

    boolean matches(Method m, Class<?> targetClass, Object[] args);
}
