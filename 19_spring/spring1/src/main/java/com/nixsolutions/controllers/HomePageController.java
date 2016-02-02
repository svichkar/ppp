package com.nixsolutions.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.dao.RentJournalDao;
import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.service.RentJournalService;

@Controller
@RequestMapping("/homepage")
public class HomePageController extends HttpServlet {

	@Autowired
	private RentJournalService rentJournalService;

	@RequestMapping(method = RequestMethod.GET)
	public String showWelcome(Model model){
		
		
		List<RentJournal> rents = rentJournalService.getAllRents();
		List<RentJournal> expiredloans = new ArrayList<>();

		for (RentJournal loan : rents) {
			DateTime dt1 = new DateTime(loan.getRentDate());
			DateTime dt2 = new DateTime();
			int returnBefore = 30;
			if (loan.getReturnDate() == null
					&& Days.daysBetween(dt1, dt2).getDays() > returnBefore) {
				loan.checkForExpiration();
				expiredloans.add(loan);
			}
		}
		model.addAttribute("loans", expiredloans);
		
		model.addAttribute("usrRole", "admin");
		
		return "HomePage";
	}
}
