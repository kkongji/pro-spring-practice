package com.jun.prospring5.ch05.simpleThrowsAdvice;

public class ErrorBean {
    public void errorProneMethod() throws Exception {
        throw new Exception("Generic Exception");
    }

    public void otherErrorProneMethod() {
        throw new IllegalArgumentException("IllegalArgumentException");
    }
}
