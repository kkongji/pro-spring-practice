package com.jun.prospring5.ch04.beanNameAware;

import org.springframework.beans.factory.BeanNameAware;

public class NamedSinger implements BeanNameAware {
    private String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public void sing() {
        System.out.println("Singer [" + name + "] - sing()");
    }
}
