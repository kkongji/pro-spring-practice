package com.jun.prospring5.ch05.composablePointcut;

import com.jun.prospring5.ch03.beanAutowiring.Guitar;
import com.jun.prospring5.ch05.cflowPointcuts.SimpleBeforeAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class ComposablePointcutDemo {
    public static void main(String[] args) {
        GrammyGuitarist johnMayer = new GrammyGuitarist();

        ComposablePointcut pc = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        System.out.println("Test 1 >>");
        GrammyGuitarist proxy = getProxy(pc, johnMayer);
        testInvoke(proxy);
        System.out.println();

        System.out.println("Test 2 >>");
        pc.union(new TalkMethodMatcher());
        proxy = getProxy(pc, johnMayer);
        testInvoke(proxy);
        System.out.println();

        System.out.println("Test 3 >>");
        pc.intersection(new RestMethodMatcher());
        proxy = getProxy(pc, johnMayer);
        testInvoke(proxy);
        System.out.println();
    }

    private static GrammyGuitarist getProxy(ComposablePointcut pc, GrammyGuitarist johnMayer) {
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);
        return (GrammyGuitarist) pf.getProxy();
    }

    private static void testInvoke(GrammyGuitarist proxy) {
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.talk();
        proxy.rest();
    }

    private static class SingMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return (method.getName().startsWith("si"));
        }
    }

    private static class TalkMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return (method.getName().equals("talk"));
        }
    }

    private static class RestMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return ("rest".equals(method.getName()));
        }
    }
}
