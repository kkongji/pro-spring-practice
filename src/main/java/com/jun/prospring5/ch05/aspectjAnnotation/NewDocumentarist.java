package com.jun.prospring5.ch05.aspectjAnnotation;

import com.jun.prospring5.ch03.beanAutowiring.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class NewDocumentarist {
    protected GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.talk();
    }

    @Autowired
    @Qualifier("johnMayer")
    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
