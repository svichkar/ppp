package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nixsolutions.studentgrade.service.SubjectService;
import com.nixsolutions.studentgrade.service.TermService;

@Controller
public class UpdateSubjectController {
	@Autowired
	SubjectService subjectService;

	@Autowired
	TermService termService;

	@RequestMapping(value = "/updateSubject")
	protected String updateSubject(@RequestParam(value = "update", required = false) String update,			
			@RequestParam(value = "updatedSubjectName", required = false) String subjectName,
			@RequestParam(value = "updatedTermName", required = false) Long termId,
			@RequestParam(value = "subject_id", required = false) Long subjectId,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (update != null) {
			subjectService.updateSubject(subjectId, subjectName, termId);
			redirectAttributes.addAttribute("message", "Updated subject with id "+ subjectId);
			return "redirect:subjects";			
		}
		model.addAttribute("updateSubject", subjectService.findSubjectById(subjectId));
		model.addAttribute("terms", termService.findAllTerms());
		return "updateSubject";
	}
}
