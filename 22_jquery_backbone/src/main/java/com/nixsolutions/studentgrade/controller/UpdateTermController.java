package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.webservice.rest.TermServiceWeb;

@Controller
public class UpdateTermController {
	@Autowired
	TermServiceWeb termServiceWeb;

	@RequestMapping(value = "/updateTerm")
	protected String updateTerm(@RequestParam(value = "update", required = false) String update,	
			@RequestParam(value = "updatedTermName", required = false) String termName,
			@RequestParam(value = "term_id", required = false) Long termId,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (update != null) {
			Term updatedTerm = termServiceWeb.findTermById(termId);
			updatedTerm.setTermName(termName);
			termServiceWeb.updateTerm(updatedTerm);
			redirectAttributes.addAttribute("message", "Updated term with id "+ termId);
			return "redirect:terms";			
		}
		model.addAttribute("updateTerm", termServiceWeb.findTermById(termId));
		return "updateTerm";
	}
}
