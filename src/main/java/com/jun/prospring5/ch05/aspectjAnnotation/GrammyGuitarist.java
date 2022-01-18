package com.jun.prospring5.ch05.aspectjAnnotation;

import com.jun.prospring5.ch03.beanAutowiring.Guitar;
import com.jun.prospring5.ch05.advisorAndPointcut.Singer;
import org.springframework.stereotype.Component;

@Component("johnMayer")
public class GrammyGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me\n " +
                "And gravity wants to bring me down");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }

    public void talk() {
        System.out.println("talk");
    }
}
