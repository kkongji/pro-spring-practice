package com.jun.prospring5.ch03.methodInjection;

import org.springframework.stereotype.Component;

@Component("singer")
public class Singer {
    private String lyric = "I played a quick game of chess with the salt and pepper shaker";

    public void sing() {
        // System.out.println(lyric);
    }
}
