package com.jun.prospring5.ch04.bootWeb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping("/")
    public String sayHi() {
        return "Hello World";
    }
}
