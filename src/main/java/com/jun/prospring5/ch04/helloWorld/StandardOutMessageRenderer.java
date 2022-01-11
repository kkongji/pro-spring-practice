package com.jun.prospring5.ch04.helloWorld;

public class StandardOutMessageRenderer implements MessageRenderer {

    private MessageProvider messageProvider;

    public StandardOutMessageRenderer() {
        System.out.println("--> StandardOutMessageRenderer 생성자 호출");
    }

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException(StandardOutMessageRenderer.class + "클래스의 messageProvider 프로퍼티를 설정해야 합니다.");
        }

        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        System.out.println("--> StandardOutMessageRenderer: messageProvider 설정");
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
