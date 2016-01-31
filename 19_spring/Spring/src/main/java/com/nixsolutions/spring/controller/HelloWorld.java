package com.nixsolutions.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Serko on 30.01.2016.
 */
@Controller
public class HelloWorld {
    @RequestMapping(value="/hello",method = {RequestMethod.POST,RequestMethod.GET})
    protected String hello(@RequestParam("name")String name, Model model){
        model.addAttribute("message", "Hello World " + name);
        return "hello";
    }
}
