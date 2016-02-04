package com.nixsolutions.controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.service.RentJournalService;


@Controller
@RequestMapping(value = "/homepage")
public class HomePageController {

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
		return "HomePage";
	}
}
