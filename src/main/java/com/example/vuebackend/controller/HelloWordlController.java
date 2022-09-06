package com.example.vuebackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordlController {
    
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "hello-world";
    }

}
