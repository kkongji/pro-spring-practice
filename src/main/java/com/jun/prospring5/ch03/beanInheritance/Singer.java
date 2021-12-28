package com.jun.prospring5.ch03.beanInheritance;

public class Singer {

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "이름='" + name + '\'' +
                ", 나이=" + age +
                '}';
    }
}
