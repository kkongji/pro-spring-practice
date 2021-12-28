package com.jun.prospring5.ch03.beanInheritance;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InheritanceDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch03/beanInheritance/app-context-xml.xml");
        ctx.refresh();

        Singer parent = ctx.getBean("parent", Singer.class);
        Singer child = ctx.getBean("child", Singer.class);

        System.out.println("부모:\n" + parent);
        System.out.println("자식:\n" + child);

    }
}
