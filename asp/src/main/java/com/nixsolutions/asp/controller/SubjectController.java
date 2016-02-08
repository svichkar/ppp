package com.nixsolutions.asp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	protected String addNewSubjectGet(Model model) {
		model.addAttribute("terms", termService.getAll());
		return "subject/addNewSubject";
	}

	@RequestMapping(value = "/create-subject", method = RequestMethod.POST)
	protected String addNewSubjectPost(@ModelAttribute("subject") String subjectName,
			@ModelAttribute("term") String term, Model model) {
		Subject subject = new Subject();
		subject.setName(subjectName);
		subject.setTerm(termService.getByTermAlias(term));
		subjectService.create(subject);
		model.addAttribute("subjects", subjectService.getAll());
		return "subject/subjects";
	}

	@RequestMapping(value = "/edit-subject", method = RequestMethod.GET)
	protected String editSubjectGet(@ModelAttribute("subjectId") String subjectId, Model model) {
		Subject subject = subjectService.getBySubjectId(Integer.parseInt(subjectId));
		model.addAttribute("subject", subject);
		model.addAttribute("terms", termService.getAll());
		return "subject/editSubject";
	}

	@RequestMapping(value = "/update-subject", method = RequestMethod.POST)
	protected String updateSubjectPost(@ModelAttribute("subjectId") String subjectId,
			@ModelAttribute("subject") String subjectName,
			@ModelAttribute("term") String termAlias, Model model) {
		Subject subject = subjectService.getBySubjectId(Integer.parseInt(subjectId));
		subject.setName(subjectName);
		subject.setTerm(termService.getByTermAlias(termAlias));
		subjectService.update(subject);
		model.addAttribute("subjects", subjectService.getAll());
		return "subject/subjects";
	}

	@RequestMapping(value = "/delete-subject", method = RequestMethod.POST)
	protected String deleteSubjectPost(@ModelAttribute("subjectId") String subjectId, Model model) {
		subjectService.delete(subjectService.getBySubjectId(Integer.parseInt(subjectId)));
		model.addAttribute("subjects", subjectService.getAll());
		return "subject/subjects";
	}
}
