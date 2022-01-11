package com.jun.prospring5.ch04.groovyConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class GroovyBeansFromJava {
    public static void main(String[] args) {
        ApplicationContext ctx = new GenericGroovyApplicationContext("classpath:spring/ch04/groovyConfig/beans.groovy");
        Singer singer = ctx.getBean("singer", Singer.class);
        System.out.println(singer);
    }
}
