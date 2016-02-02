package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by YanJun on 2015/12/23.
 */
@Controller
public class HelloWorldController {
    @RequestMapping(value="/hello")
    public String hello(){
        System.out.println("spring mvc hello world!");
        return "hello";
    }
}
