package com.jun.prospring5.ch03.beanAliases;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class AnnotatedBeanNaming {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch03/beanAliases/app-context-annotated.xml");
        ctx.refresh();

        Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);

        beans.entrySet().stream().forEach(e -> System.out.println("id: " + e.getKey() + "\n별칭: " + Arrays.toString(ctx.getAliases(e.getKey())) + "\n"));
        ctx.close();
    }
}
