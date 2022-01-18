package com.jun.prospring5.ch05.aopNamespace;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AopNamespaceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch05/aopNamespace/app-context-xml.xml");
        ctx.refresh();

        NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();

        ctx.close();
    }
}
