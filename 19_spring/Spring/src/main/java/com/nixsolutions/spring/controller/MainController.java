package com.nixsolutions.spring.controller;

import com.nixsolutions.spring.dao.TicketDAO;
import com.nixsolutions.spring.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Serko on 01.02.2016.
 */

@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    protected String mainPage(Model model) {
        model.addAttribute("overdueTickets", mainService.findOverdueTicket());
        return "homePage";
    }
}
