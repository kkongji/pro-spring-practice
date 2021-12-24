package com.jun.prospring5.ch03.methodReplacement;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacementDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch03/methodReplacement/app-context-xml.xml");
        ctx.refresh();

        ReplacementTarget replacementTarget = ctx.getBean("replacementTarget", ReplacementTarget.class);
        ReplacementTarget standardTarget = ctx.getBean("standardTarget", ReplacementTarget.class);

        displayInfo(replacementTarget);
        displayInfo(standardTarget);

        ctx.close();
    }

    private static void displayInfo(ReplacementTarget target) {
        System.out.println(target.formatMessage("Thanks for playing, try again!"));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("perTest");

        for (int x=0; x<1000000; x++) {
            String out = target.formatMessage("No filter in my head");
        }

        stopWatch.stop();
        System.out.println("1000000번 수행 시간: " + stopWatch.getTotalTimeMillis() + "ms");
    }
}
