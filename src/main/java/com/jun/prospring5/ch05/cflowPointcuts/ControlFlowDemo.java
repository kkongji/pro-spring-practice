package com.jun.prospring5.ch05.cflowPointcuts;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ControlFlowDemo {
    public static void main(String[] args) {
        ControlFlowDemo ex = new ControlFlowDemo();
        ex.run();
    }

    public void run() {
        TestBean target = new TestBean();
        // ControlFlowDemo.test()가 호출하는 모든 메서드에 포인트컷을 적용한다.
        Pointcut pc = new ControlFlowPointcut(ControlFlowDemo.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target);

        TestBean proxy = (TestBean) pf.getProxy();
        System.out.println("\tTrying normal invoke");
        proxy.foo();
        System.out.println("\nTrying under ControlFlowDemo.test()");
        test(proxy);
    }

    private void test(TestBean proxy) {
        proxy.foo();
    }
}
