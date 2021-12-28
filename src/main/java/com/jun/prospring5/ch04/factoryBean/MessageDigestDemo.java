package com.jun.prospring5.ch04.factoryBean;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch04/factoryBean/app-context-xml.xml");
        ctx.refresh();

        MessageDigester bean = ctx.getBean("digester", MessageDigester.class);
        bean.digest("Hello World");

        ctx.close();
    }
}
