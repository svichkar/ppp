package com.nixsolutions.spring.controller;

import com.nixsolutions.spring.service.AddCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kozlovskij on 2/3/2016.
 */
@Controller
public class AddCategoryController {
    @Autowired
    AddCategoryService addCategoryService;

    @RequestMapping(value = "/categoryManagement")
    protected String addUser(
            @RequestParam(value = "categoryName", required = false) String categoryName,
            Model model) {
        if (categoryName != null) {
            model.addAttribute("msg", "Category with id " + addCategoryService.create(categoryName) + " added");
        }
        model.addAttribute("categories", addCategoryService.categories());
        return "addCategory";
    }
}
