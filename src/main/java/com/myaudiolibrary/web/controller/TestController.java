package com.myaudiolibrary.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(final ModelMap m)
    {
        m.put("title", "This is Title Test");
        m.put("n", "Pierre,");
        m.put("msg", "How are <strong>you</strong>");
        m.put("paragraphe", "Bienvenue sur l'application web java Thymeleaf");

        return "test";
    }
}
