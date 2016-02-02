package com.nixsolutions.spring.controller;

import com.nixsolutions.spring.dao.TicketDAO;
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
    private TicketDAO ticketDAO;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("overdueTickets", ticketDAO.findOverdueTicket());
        return "homePage";
    }
}
