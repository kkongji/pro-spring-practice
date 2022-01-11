package com.jun.prospring5.ch04.propertyEditors;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomEditorExample {
    private FullName name;

    public FullName getName() {
        return name;
    }

    public void setName(FullName name) {
        this.name = name;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch04/propertyEditors/app-context-02.xml");
        ctx.refresh();
        CustomEditorExample bean = ctx.getBean("exampleBean", CustomEditorExample.class);

        System.out.println(bean.getName());
    }
}
