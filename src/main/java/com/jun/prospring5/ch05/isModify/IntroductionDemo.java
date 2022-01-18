package com.jun.prospring5.ch05.isModify;

import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public class IntroductionDemo {
    public static void main(String[] args) {
        Contact target = new Contact();
        target.setName("John Mayer");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target);
        pf.setOptimize(true);

        Contact proxy = (Contact) pf.getProxy();
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
