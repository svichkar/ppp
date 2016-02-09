package com.nixsolutions.asp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.asp.entity.Subject;
import com.nixsolutions.asp.service.SubjectService;
import com.nixsolutions.asp.service.TermService;

@Controller
@RequestMapping(value = "/subjects")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TermService termService;

	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	protected String subjectGet(Model model) {
		model.addAttribute("subjects", subjectService.getAll());
		return "subject/subjects";
	}

	@RequestMapping(value = "/subjects", method = RequestMethod.POST)
	protected String subjectPost(@ModelAttribute("searchType") String searchType,
			@ModelAttribute("searchQuery") String searchQuery, Model model) {
        model.addAttribute("subjects", subjectService.getSubjectsByQuery(searchType, searchQuery));
		model.addAttribute("radioSearch", searchType);
		return "subject/subjects";
	}

	@RequestMapping(value = "/add-new-subject", method = RequestMethod.GET)
	protected ModelAndView addNewSubjectGet() {
		Subject subject = new Subject();
		ModelAndView model = new ModelAndView("subject/addNewSubject", "SubjectModel", subject);		
		model.addObject("termList", termService.getTermMap());
		return model;		
	}

	@RequestMapping(value = "/create-subject", method = RequestMethod.POST)
	protected String addNewSubjectPost(@ModelAttribute("SubjectModel") Subject subject, Model model) {
		subjectService.create(subject);
		return "redirect:/subjects/subjects";
	}

	@RequestMapping(value = "/edit-subject", method = RequestMethod.GET)
	protected ModelAndView editSubjectGet(@ModelAttribute("subjectId") String subjectId) {
		Subject subject = subjectService.getBySubjectId(Integer.parseInt(subjectId));
		ModelAndView model = new ModelAndView("subject/editSubject", "SubjectModel", subject);		
		model.addObject("termList", termService.getTermMap());
		return model;
	}

	@RequestMapping(value = "/update-subject", method = RequestMethod.POST)
	protected String updateSubjectPost(@ModelAttribute("SubjectModel") Subject subject, Model model) {		
		subjectService.update(subject);
		return "redirect:/subjects/subjects";
	}

	@RequestMapping(value = "/delete-subject", method = RequestMethod.POST)
	protected String deleteSubjectPost(@ModelAttribute("subjectId") String subjectId, Model model) {
		subjectService.delete(subjectService.getBySubjectId(Integer.parseInt(subjectId)));
		return "redirect:/subjects/subjects";
	}
}
