package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.entity.Term;
import com.nixsolutions.service.TermService;

@Controller
@RequestMapping(value = "/terms")
public class TermController {
	@Autowired
	private TermService termService;

	@RequestMapping(value = "/terms.do", method = { RequestMethod.GET, RequestMethod.POST })
	protected String termGet(Model model) {
		model.addAttribute("terms", termService.getAll());
		return "/WEB-INF/jsp/term/Terms.jsp";
	}

	@RequestMapping(value = "/addNewTerm.do", method = RequestMethod.GET)
	protected String addNewTermGet(Model model) {
		return "/WEB-INF/jsp/term/AddNewTerm.jsp";
	}

	@RequestMapping(value = "/addNewTerm.do", method = RequestMethod.POST)
	protected String addNewTermPost(@ModelAttribute("alias") String alias, Model model) {
		Term term = new Term();
		term.setAlias(alias);
		termService.create(term);
		model.addAttribute("terms", termService.getAll());
		return "/WEB-INF/jsp/term/Terms.jsp";
	}

	@RequestMapping(value = "/editTerm.do", method = RequestMethod.GET)
	protected String editTermGet(@ModelAttribute("termId") String termId, Model model) {
		Term term = termService.getByTermId(Integer.parseInt(termId));
		model.addAttribute("term", term);
		return "/WEB-INF/jsp/term/EditTerm.jsp";
	}

	@RequestMapping(value = "/editTerm.do", method = RequestMethod.POST)
	protected String editTermPost(@ModelAttribute("termId") String termId,
			@ModelAttribute("alias") String alias, Model model) {
		Term term = termService.getByTermId(Integer.parseInt(termId));
		term.setAlias(alias);
		termService.update(term);
		model.addAttribute("terms", termService.getAll());
		return "/WEB-INF/jsp/term/Terms.jsp";
	}

	@RequestMapping(value = "/deleteTerm.do", method = RequestMethod.POST)
	protected String deleteTermPost(@ModelAttribute("termId") String termId, Model model) {
		termService.delete(termService.getByTermId(Integer.parseInt(termId)));
		model.addAttribute("terms", termService.getAll());
		return "/WEB-INF/jsp/term/Terms.jsp";
	}
}
