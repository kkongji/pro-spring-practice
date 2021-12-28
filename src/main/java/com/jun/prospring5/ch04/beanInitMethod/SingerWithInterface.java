package com.jun.prospring5.ch04.beanInitMethod;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SingerWithInterface implements InitializingBean {

    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("빈 초기화");

        if (name == null) {
            System.out.println("기본 이름 사용");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(Singer.class + "빈 타입에는 반드시 age 프로퍼티를 설정해야 합니다");
        }
    }

    @Override
    public String toString() {
        return "Singer{" +
                "이름='" + name + '\'' +
                ", 나이=" + age +
                '}';
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch04/beanInitMethod/app-context-xml.xml");
        ctx.refresh();

        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);
    }

    private static SingerWithInterface getBean(String beanName, ApplicationContext ctx) {
        try {
            SingerWithInterface bean = ctx.getBean(beanName, SingerWithInterface.class);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException e) {
            System.out.println("빈 구성 도중 에러 발생: " + e.getMessage());
            return null;
        }
    }
}
