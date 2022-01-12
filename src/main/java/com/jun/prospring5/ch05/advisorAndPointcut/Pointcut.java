package com.jun.prospring5.ch05.advisorAndPointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;

public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
