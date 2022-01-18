package com.jun.prospring5.ch05.aopNamespace;

import com.jun.prospring5.ch03.beanAutowiring.Guitar;
import com.jun.prospring5.ch05.proxyfactorybean.Documentarist;

public class NewDocumentarist extends Documentarist {
    @Override
    public void execute() {
        guitarist.sing();
        guitarist.sing(new Guitar());
        guitarist.talk();
    }
}
