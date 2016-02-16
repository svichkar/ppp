package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.webservice.rest.TermServiceWeb;

@Controller
public class TermsController {

	@Autowired
	TermServiceWeb termServiceWeb;
	
	@RequestMapping(value = "/terms")
	protected String terms(@RequestParam(value = "add", required = false) String add,
			@RequestParam(value = "delete", required = false) String delete,
			@RequestParam(value = "newTermName", required = false) String termName,
			@RequestParam(value = "term_id", required = false) Long termId,
			@RequestParam(value = "message", required = false) String message,
			Model model) {
		
		if (message != null){
			model.addAttribute("message", message);
		}
			
		if (add != null) {
			Term newTerm = new Term();
			newTerm.setTermName(termName);
			termServiceWeb.createTerm(newTerm);
			model.addAttribute("message", "Added term with id "
					+ newTerm.getTermId());
		}
		if (delete != null) {
			termServiceWeb.deleteTerm(termServiceWeb.findTermById(termId));
			model.addAttribute("message", "Deleted term with id " + termId);
		}	
			
		model.addAttribute("terms", termServiceWeb.findAllTerms());
		return "terms";
	}

}
