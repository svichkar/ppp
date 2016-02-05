package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.studentgrade.service.SubjectService;
import com.nixsolutions.studentgrade.service.TermService;

@Controller
public class SubjectsController {
	@Autowired
	SubjectService subjectService;

	@Autowired
	TermService termService;
	
	@RequestMapping(value = "/subjects")
	protected String subjects(@RequestParam(value = "add", required = false) String add,
			@RequestParam(value = "delete", required = false) String delete,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "newSubjectName", required = false) String subjectName,
			@RequestParam(value = "newTermName", required = false) Long termId,
			@RequestParam(value = "subject_id", required = false) Long subjectId,
			@RequestParam(value = "searchSubjectName", required = false) String searchSubjectName,
			@RequestParam(value = "searchTermId", required = false) Long searchTermId,
			@RequestParam(value = "message", required = false) String message,
			Model model) {
		
		if (message != null){
			model.addAttribute("message", message);
		}
			
		if (add != null) {
			model.addAttribute("message", "Added subject with id "
					+ subjectService.createSubject(subjectName, termId));
		}
		if (delete != null) {
			subjectService.deleteSubject(subjectId);
			model.addAttribute("message", "Deleted subject with id " + subjectId);
		}		
		
		model.addAttribute("subjects", subjectService.findAllSubjects());
		
		if (search != null) {
			if (searchSubjectName != null && searchTermId != null) {
				model.addAttribute("subjects", subjectService.findSubjectByNameAndTermId(searchSubjectName, searchTermId));
			}

			if (searchSubjectName != null && searchTermId == null) {
				model.addAttribute("subjects", subjectService.findSubjectsByName(searchSubjectName));
			}

			if (searchTermId != null && searchSubjectName.isEmpty()) {
				model.addAttribute("subjects", subjectService.findSubjectsByTermId(searchTermId));
			}
		}
		
		model.addAttribute("terms", termService.findAllTerms());
		return "subjects";
	}

}
