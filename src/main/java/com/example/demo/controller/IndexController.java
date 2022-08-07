package com.example.demo.controller;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(value = "session")
@Component(value = "indexController")
public class IndexController {

    @Getter
    private String message;

    public void sayHelloWorld() {
        message = "HELLO WORLD!";
    }
}
