package com.nixsolutions.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Serko on 01.02.2016.
 */
@Controller
public class MainController {
    @RequestMapping("/main")
    public String main (@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "mainPage";
    }
}
