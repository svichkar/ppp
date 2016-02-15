package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nixsolutions.studentgrade.service.TermService;

@Controller
public class UpdateTermController {
	@Autowired
	TermService termService;

	@RequestMapping(value = "/updateTerm")
	protected String updateTerm(@RequestParam(value = "update", required = false) String update,	
			@RequestParam(value = "updatedTermName", required = false) String termName,
			@RequestParam(value = "term_id", required = false) Long termId,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (update != null) {
			termService.updateTerm(termId, termName);
			redirectAttributes.addAttribute("message", "Updated term with id "+ termId);
			return "redirect:terms";			
		}
		model.addAttribute("updateTerm", termService.findTermById(termId));
		return "updateTerm";
	}
}
