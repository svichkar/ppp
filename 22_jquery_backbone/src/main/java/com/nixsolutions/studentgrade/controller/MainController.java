package com.nixsolutions.studentgrade.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping(value = "/main")
    protected String mainPage(HttpServletRequest request) {
    	String mainPage=null;
    	if (request.isUserInRole("ROLE_ADMIN")) 
    		mainPage="redirect:users";        
    	if (request.isUserInRole("ROLE_USER")) 
    		mainPage="redirect:students";
    	return mainPage;                 
    } 

}
