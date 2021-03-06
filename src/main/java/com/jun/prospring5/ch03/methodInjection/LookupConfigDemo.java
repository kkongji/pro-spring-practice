package com.jun.prospring5.ch03.methodInjection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StopWatch;

public class LookupConfigDemo {

    @Configuration
    @ComponentScan(basePackages = {"com.jun.prospring5.ch03.methodInjection"})
    public static class LookupConfig {
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(LookupConfig.class);

        DemoBean abstractBean = ctx.getBean("abstractLookupBean", DemoBean.class);
        DemoBean standardBean = ctx.getBean("standardLookupBean", DemoBean.class);
        displayInfo("abstractLookupBean", abstractBean);
        displayInfo("standardLookupBean", standardBean);
        ctx.close();
    }

    private static void displayInfo(String beanName, DemoBean bean) {
        Singer singer1 = bean.getMySinger();
        Singer singer2 = bean.getMySinger();

        System.out.println("" + beanName + ":Singer 인스턴스는 같은가?" + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");
        for (int x = 0; x < 100000; x++) {
            Singer singer = bean.getMySinger();
            singer.sing();
        }

        stopWatch.stop();

        System.out.println("100000번을 얻어오는 데 걸린 시간" + stopWatch.getTotalTimeMillis() + "ms");
    }
}
