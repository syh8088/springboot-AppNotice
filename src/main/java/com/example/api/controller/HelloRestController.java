package com.example.api.controller;

import com.example.api.domain.UserGrade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @RequestMapping("/")
    public ResponseEntity<?> index1() {

        return ResponseEntity.ok("helloworld!");
    }
}