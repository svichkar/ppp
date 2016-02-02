package com.nixsolutions.spring.controller;

import com.nixsolutions.spring.service.AddReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kozlovskij on 2/2/2016.
 */
@Controller
public class AddReaderController {
    @Autowired
    AddReaderService addReaderService;

    @RequestMapping(value = "/addReader")
    protected String addReader(
            @RequestParam(value = "clientFirstName", required = false) String firstName,
            @RequestParam(value = "clientLastName", required = false) String lastName,
            @RequestParam(value = "clientPhone", required = false) String phone,
            @RequestParam(value = "clientEmail", required = false) String email,
            Model model) {
        if (firstName != null) {
            model.addAttribute("msg", "user with id " + addReaderService.addClient(firstName, lastName, phone, email) + " added");
        }
        return "addReader";
    }
}
