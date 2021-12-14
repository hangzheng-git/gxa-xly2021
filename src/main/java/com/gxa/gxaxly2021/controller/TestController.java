package com.gxa.gxaxly2021.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("test")
    public String index(){
        return "Hello SpringBoot!";
    }
}
