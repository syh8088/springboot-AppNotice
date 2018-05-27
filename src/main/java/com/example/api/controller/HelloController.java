package com.example.api.controller;

import com.example.api.repositories.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private BlogDao blogDao;

    @RequestMapping("/index")
    public String index2() {
        blogDao.insertNewContent("Hello Spring?");
        return "Greetings from Spring Boot!";
    }

}