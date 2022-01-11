package com.jun.prospring5.ch04.events;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Publisher implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public void publish(String message) {
        ctx.publishEvent(new MessageEvent(this, message));
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ch04/events/app-context-xml.xml");
        Publisher pub = ctx.getBean("publisher", Publisher.class);
        pub.publish("나는 세상에 SOS를 보낸다");
        pub.publish("... 나는 누군가가 병에 담긴");
        pub.publish("... 이 메시지를 받았으면 한다.");
    }
}
