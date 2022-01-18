package com.jun.prospring5.ch05.annotationPointcut;

import com.jun.prospring5.ch03.beanAutowiring.Guitar;
import com.jun.prospring5.ch05.advisorAndPointcut.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        AnnotationMatchingPointcut pc = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(johnMayer);

        Guitarist proxy = (Guitarist) pf.getProxy();

        proxy.sing(new Guitar());
        proxy.rest();
    }
}
