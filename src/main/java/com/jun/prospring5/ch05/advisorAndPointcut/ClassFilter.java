package com.jun.prospring5.ch05.advisorAndPointcut;

public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
