package com.jun.prospring5.ch05.proxyfactorybean;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch05/proxyfactorybean/app-context-xml.xml");
        ctx.refresh();

        Documentarist documentaristOne = ctx.getBean("documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = ctx.getBean("documentaristTwo", Documentarist.class);

        System.out.println("documentaristOne >>");
        documentaristOne.execute();

        System.out.println("documentaristTwo >>");
        documentaristTwo.execute();
    }
}
