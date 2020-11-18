package com.myaudiolibrary.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sayHello")
public class HelloController {

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "text/plaine")
    public String hello(){
        return "hello world";
    }

    @RequestMapping(value = "/html", method = RequestMethod.GET, produces = "text/html")
    public String helloHtml(){
        return "<h1>Hello World</h1> <p>Ca fonctionne en html également</p>";
    }
}
