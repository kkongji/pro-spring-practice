package com.jun.prospring5.ch05.advisorAndPointcut;

public class GoodGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Who says I can't bee free\n" + "From all of the things that I used to be");
    }
}
