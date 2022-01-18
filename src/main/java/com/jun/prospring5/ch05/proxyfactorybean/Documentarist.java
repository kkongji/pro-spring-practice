package com.jun.prospring5.ch05.proxyfactorybean;

public class Documentarist {
    protected GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }

}
