package com.jun.prospring5.ch03.beanAliases;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class AliasConfigDemo {

    @Configuration
    public static class AliasBeanConfig{
        @Bean(name = { "johnMayer", "john", "jonathan", "johnny" })
        public Singer singer() {
            return new Singer();
        }
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AliasBeanConfig.class);
        Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);

        beans.entrySet().stream().forEach(e -> System.out.println("id: " + e.getKey() + "\n별칭: " + Arrays.toString(ctx.getAliases(e.getKey())) + "\n"));
        ctx.close();
    }
}
