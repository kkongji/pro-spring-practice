package com.jun.prospring5.ch03.beanAliases;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("johnMayer")
public class Singer {
    private String lyric = "We found a message in a bottle we were drinking";

    public void setLyric(@Value("i'M busted up but I'm loving every minute of it") String lyric) {
        this.lyric = lyric;
    }

    public void sing() {
        System.out.println(lyric);
    }
}
