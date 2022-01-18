package com.jun.prospring5.ch05.aspectjAspects;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch05/aspectjAspects/app-context-xml.xml");
        ctx.refresh();

        MessageWriter writer = new MessageWriter();
        writer.writeMessage();
        writer.foo();

    }
}
