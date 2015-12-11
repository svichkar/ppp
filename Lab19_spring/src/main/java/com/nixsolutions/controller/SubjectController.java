package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.entity.Subject;
import com.nixsolutions.service.SubjectService;
import com.nixsolutions.service.TermService;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TermService termService;

	@RequestMapping(value = "/Subjects.do", method = RequestMethod.GET)
	protected String subjectGet(Model model) {
		model.addAttribute("subjects", subjectService.getAll());
		return "/WEB-INF/jsp/subject/Subjects.jsp";
	}

	@RequestMapping(value = "/Subjects.do", method = RequestMethod.POST)
	protected String subjectPost(@ModelAttribute("searchType") String searchType,
			@ModelAttribute("searchQuery") String searchQuery, Model model) {
		if (searchType.equals("subject")) {
			model.addAttribute("subjects", subjectService.getBySubjectName(searchQuery));
		} else {
			model.addAttribute("subjects", subjectService.getSubjectsByTerm(termService.getByTermAlias(searchQuery)));
		}
		return "/WEB-INF/jsp/subject/Subjects.jsp";
	}

	@RequestMapping(value = "/addNewSubject.do", method = RequestMethod.GET)
	protected String addNewSubjectGet(Model model) {
		return "/WEB-INF/jsp/subject/AddNewSubject.jsp";
	}

	@RequestMapping(value = "/addNewSubject.do", method = RequestMethod.POST)
	protected String addNewSubjectPost(@ModelAttribute("subject") String subjectName,
			@ModelAttribute("term") String term, Model model) {
		Subject subject = new Subject();
		subject.setName(subjectName);
		subject.setTerm(termService.getByTermAlias(term));
		subjectService.create(subject);
		model.addAttribute("subjects", subjectService.getAll());
		return "/WEB-INF/jsp/subject/Subject.jsp";
	}

	@RequestMapping(value = "/editSubject.do", method = RequestMethod.GET)
	protected String editSubjectGet(@ModelAttribute("subjectId") String subjectId, Model model) {
		Subject subject = subjectService.getBySubjectId(Integer.parseInt(subjectId));
		model.addAttribute("subject", subject);
		return "/WEB-INF/jsp/subject/EditSubject.jsp";
	}

	@RequestMapping(value = "/editSubject.do", method = RequestMethod.POST)
	protected String editSubjectPost(@ModelAttribute("subjectId") String subjectId, 
			@ModelAttribute("subject") String subjectName,
			@ModelAttribute("term") String termAlias, Model model) {
		Subject subject = subjectService.getBySubjectId(Integer.parseInt(subjectId));
		subject.setName(subjectName);
		subject.setTerm(termService.getByTermAlias(termAlias));
		subjectService.update(subject);
		model.addAttribute("subjects", subjectService.getAll());
		return "/WEB-INF/jsp/subject/Subject.jsp";
	}

	@RequestMapping(value = "/deleteSubject.do", method = RequestMethod.POST)
	protected String deleteSubjectPost(@ModelAttribute("subjectId") String subjectId, Model model) {
		subjectService.delete(subjectService.getBySubjectId(Integer.parseInt(subjectId)));
		model.addAttribute("subjects", subjectService.getAll());
		return "/WEB-INF/jsp/subject/Subject.jsp";
	}
}
