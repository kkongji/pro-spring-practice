package com.jun.prospring5.ch04.beanNameAware;

import org.springframework.context.support.GenericXmlApplicationContext;

public class NamedSingerDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch04/beanNameAware/app-context-xml.xml");
        ctx.refresh();

        NamedSinger bean = ctx.getBean("johnMayer", NamedSinger.class);
        bean.sing();

        ctx.close();
    }
}
