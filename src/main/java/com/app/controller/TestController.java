package com.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController {

    @PostMapping("/auth/test")
    public String test() {
        return "OK";
    }
}