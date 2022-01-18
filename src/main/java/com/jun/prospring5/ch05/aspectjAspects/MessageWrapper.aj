package com.jun.prospring5.ch05.aspectjAspects;

public aspect MessageWrapper {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    pointcut doWriting() : execution(* com.jun.prospring5.ch05.aspectjAspects.MessageWriter.writeMessage());

    before(): doWriting() {
        System.out.println(prefix);
    }

    after(): doWriting() {
        System.out.println(suffix);
    }

}
