package com.jun.prospring5.ch05.simpleAroundAdvice;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {
    public static void main(String[] args) {
        WorkerBean bean = getWorkerBean();
        bean.doSOmeWork(10000000);
    }

    private static WorkerBean getWorkerBean() {
        WorkerBean target = new WorkerBean();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new ProfilingInterceptor());
        pf.setTarget(target);

        return (WorkerBean) pf.getProxy();
    }

}
