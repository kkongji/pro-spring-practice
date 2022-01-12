package com.jun.prospring5.ch03.beanAutowiring;

import org.springframework.stereotype.Component;

@Component("gopher")
public class Guitar {

    public void sing() {
        System.out.println("Cm Eb Fm Ab Bb");
    }

    public String play() {
        return "G C G C Am D7";
    }
}
