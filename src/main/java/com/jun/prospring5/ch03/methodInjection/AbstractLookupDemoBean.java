package com.jun.prospring5.ch03.methodInjection;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public class AbstractLookupDemoBean implements DemoBean {

    @Lookup("singer")
    public Singer getMySinger() {
        return null;
    };

    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}
