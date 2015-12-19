package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.service.JournalService;

@Controller
public class JournalController {
	
	@Autowired
	private JournalService journalService;
	
	@RequestMapping(value = "/Journal.do", method = RequestMethod.GET)
	protected String journalGet(Model model) {
		model.addAttribute("journal", journalService.getAll());
		return "/WEB-INF/jsp/journal/Journal.jsp";
	}

	@RequestMapping(value = "/Journal.do", method = RequestMethod.POST)
	protected String journalPost(@ModelAttribute("studentId") String studentId, Model model) {
		model.addAttribute("journal", journalService.getJournalByStudentId(Integer.parseInt(studentId)));
		return "/WEB-INF/jsp/journal/Journal.jsp";
	}
}