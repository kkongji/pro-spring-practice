package com.jun.prospring5.ch05.proxyfactorybean;

import com.jun.prospring5.ch05.isModify.Contact;
import com.jun.prospring5.ch05.isModify.IsModified;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IntroductionConfigDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Contact proxy = (Contact) ctx.getBean("bean");
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("Is Contact?: " + (proxy instanceof Contact));
        System.out.println("Is IsModified?: " + (proxy instanceof IsModified));

        System.out.println("Has been modified?: " + proxyInterface.isModified());

        proxy.setName("John Mayer");

        System.out.println("Has Been Modified?: " + proxyInterface.isModified());

        proxy.setName("Eric Clapton");
        System.out.println("Has been modified?: " + proxyInterface.isModified());
    }
}
