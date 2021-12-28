package com.jun.prospring5.ch04.factoryBean;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AccessingFactoryBeans {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch04/factoryBean/app-context-xml.xml");
        ctx.refresh();
        ctx.getBean("shaDigest", MessageDigest.class);

        MessageDigestFactoryBean factoryBean = ctx.getBean("&shaDigest", MessageDigestFactoryBean.class);
        try {
            MessageDigest shaDigest = factoryBean.getObject();
            System.out.println(shaDigest.digest("Hello World".getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ctx.close();

    }
}
