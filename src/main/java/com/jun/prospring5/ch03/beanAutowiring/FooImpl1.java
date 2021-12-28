package com.jun.prospring5.ch03.beanAutowiring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("fooImpl1")
@Primary
public class FooImpl1 implements Foo{
}
