package com.nixsolutions.spring.controller;

import com.nixsolutions.spring.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kozlovskij on 2/2/2016.
 */
@Controller
public class LoanController {
    @Autowired
    private LoanService loanService;

    @RequestMapping(value = "/loanManagement")
    protected String loanBook(
            @RequestParam(value = "bookId", required = false) Long[] bookIds,
            @RequestParam(value = "giveBook", required = false) String giveBook,
            @RequestParam(value = "client" , required = false) Long clientId,
            Model model) {
        model.addAttribute("clients", loanService.clients());
        if (bookIds != null) {
            if (!giveBook.equals("true")) {
                model.addAttribute("books", loanService.checkedBooks(bookIds));
            } else {
                loanService.createTicket(bookIds, clientId);
                model.addAttribute("msg", "new ticket added");
            }
        }
        return "loanBook";
    }
}
