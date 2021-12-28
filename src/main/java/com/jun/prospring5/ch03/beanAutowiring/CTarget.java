package com.jun.prospring5.ch03.beanAutowiring;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CTarget {
    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public CTarget() {
        System.out.println("target() 호출");
    }

    public CTarget(Foo foo) {
        System.out.println("target(Foo foo) 호출");
    }

    public CTarget(Foo foo, Bar bar) {
        System.out.println("target(Foo foo, Bar bar) 호출");
    }

    public void setFooOne(Foo fooOne) {
        this.fooOne = fooOne;
        System.out.println("Property fooOne 설정");
    }

    public void setFooTwo(Foo fooTwo) {
        this.fooTwo = fooTwo;
        System.out.println("Property fooTwo 설정");
    }

    public void setBar(Bar bar) {
        this.bar = bar;
        System.out.println("Property bar 설정");
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch03/beanAutowiring/app-context-04.xml");
        ctx.refresh();

        System.out.println("\nUsing byType:\n");
        CTarget t = ctx.getBean("targetByType", CTarget.class);

        ctx.close();

    }
}
