package com.jun.prospring5.ch03.beanInstantiationMode;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class NonSingletonDemo {

//    @Configuration
//    @ComponentScan(basePackages = { "com.jun.prospring5.ch03.beanInstantiationMode" })
    public static class Config {}

    public static void main(String[] args) {
//        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch03/beanInstantiationMode/app-context-xml.xml");
        ctx.refresh();

        Singer nonSingleton = ctx.getBean("nonSingleton", Singer.class);
        Singer nonSingleton1 = new Singer("John Mayer");

        System.out.println("식별자가 동일한가? " + (nonSingleton == nonSingleton1));
        System.out.println("값이 동일한가? " + nonSingleton.equals(nonSingleton1));
        System.out.println(nonSingleton);
        System.out.println(nonSingleton1);

        ctx.close();
    }
}
