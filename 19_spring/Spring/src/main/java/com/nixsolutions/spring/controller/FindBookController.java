package com.nixsolutions.spring.controller;

import com.nixsolutions.spring.service.FindBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by kozlovskij on 2/2/2016.
 */
@Controller
public class FindBookController {
    @Autowired
    FindBookService findBookService;

    @RequestMapping(value = "/bookManagement")
    protected String findBook(
            @RequestParam(value = "searchCriteria", required = false) String searchCriteria,
            @RequestParam(value = "searchWord", required = false) String searchWord,
            Model model) {
        if (searchCriteria != null) {
            model.addAttribute("books", findBookService.find(searchCriteria, searchWord));
        }
        if (searchCriteria != null && !searchCriteria.equals("all") && searchWord != null) {
            model.addAttribute("msg", "Empty searchWord, try again");
        }
        return "findBook";
    }

}
