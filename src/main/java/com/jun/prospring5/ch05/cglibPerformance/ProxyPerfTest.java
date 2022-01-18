package com.jun.prospring5.ch05.cglibPerformance;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ProxyPerfTest {
    public static void main(String[] args) {
        SimpleBean target = new DefaultSimpleBean();

        Advisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvice());

        runCglibTests(advisor, target);
        runCglibFrozenTests(advisor, target);
        runJdkTests(advisor, target);
    }

    private static void runCglibTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.addAdvisor(advisor);
        pf.setTarget(target);
        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("Running CHLIB (Standard) Tests");
        test(proxy);
    }

    private static void runCglibFrozenTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.addAdvisor(advisor);
        pf.setTarget(target);
        pf.setFrozen(true);

        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("Running CHLIB (Frozen) Tests");
        test(proxy);
    }

    private static void runJdkTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target);
        pf.setInterfaces(SimpleBean.class);

        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("Running JDK Tests");
        test(proxy);
    }

    private static void test(SimpleBean bean) {
        long before = 0;
        long after = 0;

        System.out.println("Testing Advised Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            bean.advised();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + "ms");

        System.out.println("Testing Unadvised Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            bean.unadvised();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + "ms");

        System.out.println("Testing equals() Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            bean.equals(bean);
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + "ms");

        System.out.println("Testing hashCode() Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            bean.hashCode();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + "ms");

        Advised advised = (Advised) bean;
        System.out.println("Testing Advised.getProxyTargetClass() Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            advised.getTargetClass();
        }
        after = System.currentTimeMillis();

        System.out.println("Took " + (after - before) + "ms");
        System.out.println(">>>\n");
    }
}
