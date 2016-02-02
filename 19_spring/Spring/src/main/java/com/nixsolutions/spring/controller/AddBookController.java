package com.nixsolutions.spring.controller;

import com.nixsolutions.spring.service.AddBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kozlovskij on 2/2/2016.
 */
@Controller
public class AddBookController {
    @Autowired
    AddBookService addBookService;

    @RequestMapping(value = "/addBook")
    protected String addBook(
            @RequestParam(value = "bookName", required = false) String bookName,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "cellId", required = false) Long cellId,
            @RequestParam(value = "authorId", required = false) Long[] authorId,
            @RequestParam(value = "bookQuantity", required = false) Integer bookQuantity,
            Model model) {
        model.addAttribute("authors", addBookService.authors());
        model.addAttribute("categories", addBookService.categories());
        model.addAttribute("cells", addBookService.cells());
        if (bookName != null) {
            addBookService.addBook(bookName, categoryId, cellId, authorId, bookQuantity);
            model.addAttribute("msg", bookQuantity + " books added");
        }
        return "addBook";
    }
}
