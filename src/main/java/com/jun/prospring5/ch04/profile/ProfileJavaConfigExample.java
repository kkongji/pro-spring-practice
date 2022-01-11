package com.jun.prospring5.ch04.profile;

import com.jun.prospring5.ch04.profile2.KindergartenConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class ProfileJavaConfigExample {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(HighschoolConfig.class, KindergartenConfig.class);
        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);
        List<Food> foods = foodProviderService.provideLunchSet();
        for (Food food : foods) {
            System.out.println("food: " + food.getName());
        }

    }
}
