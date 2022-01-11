package com.jun.prospring5.ch04.helloWorld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {
    private String message;

    public ConfigurableMessageProvider(@Value("Love on the weekend") String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
