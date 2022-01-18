package com.jun.prospring5.ch05.aspectjAspects;

public class MessageWriter {
    public void writeMessage() {
        System.out.println("foobar!");
    }

    public void foo() {
        System.out.println("foo");
    }
}
