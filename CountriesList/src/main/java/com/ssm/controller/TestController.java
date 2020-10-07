package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("helloSSM")
    public String hello(){
        System.out.printf("123");
        return "success";
    }
}
