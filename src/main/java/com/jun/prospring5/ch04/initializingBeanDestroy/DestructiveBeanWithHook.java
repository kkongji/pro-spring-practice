package com.jun.prospring5.ch04.initializingBeanDestroy;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DestructiveBeanWithHook {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("");
        ctx.refresh();

        ctx.getBean(DestructiveBeanWithHook.class);
        ctx.registerShutdownHook();
    }
}
