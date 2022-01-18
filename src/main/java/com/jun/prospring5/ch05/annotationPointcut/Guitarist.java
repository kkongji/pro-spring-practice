package com.jun.prospring5.ch05.annotationPointcut;

import com.jun.prospring5.ch03.beanAutowiring.Guitar;
import com.jun.prospring5.ch05.advisorAndPointcut.Singer;

public class Guitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Dream of ways to throw it all away");
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }
}
