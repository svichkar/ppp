package com.nixsolutions.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kozlovskij on 2/2/2016.
 */
@Controller
public class AddReader {
    @RequestMapping(value = "/addReader")
    protected String findBook(
            @RequestParam(value = "bookName", required = false) String bookName,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "cellId", required = false) Long cellId,
            @RequestParam(value = "authorId", required = false) Long[] authorId,
            @RequestParam(value = "bookQuantity", required = false) Integer bookQuantity,
            Model model) {
        return "addReader";
}}
