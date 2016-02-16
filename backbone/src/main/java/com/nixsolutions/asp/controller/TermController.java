package com.nixsolutions.asp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.asp.entity.Term;
import com.nixsolutions.asp.service.TermService;

@Controller
@RequestMapping(value = "/terms")
public class TermController {
	
	@Autowired
	private TermService termService;

	@RequestMapping(value = "/terms", method = { RequestMethod.GET, RequestMethod.POST })
	protected String termGet(Model model) {
		model.addAttribute("terms", termService.getAll());
		return "term/terms";
	}

	@RequestMapping(value = "/add-new-term", method = RequestMethod.GET)
	protected ModelAndView addNewTermGet(@RequestParam(value = "error", required = false) String error) {
		Term term = new Term();
		ModelAndView model = new ModelAndView("term/addNewTerm", "TermModel", term);
		if (error != null) {
			model.addObject("error", "Term with such alias already exists!");
		}
		return model;
	}

	@RequestMapping(value = "/create-term", method = RequestMethod.POST)
	protected String addNewTermPost(@ModelAttribute("TermModel")Term term, Model model) {
		if(termService.getByTermAlias(term.getAlias()) != null){
			model.addAttribute("error", "error");
			return "redirect:/terms/add-new-term";
		}		
		termService.create(term);
		return "redirect:/terms/terms";
		//model.addAttribute("terms", termService.getAll());
		//return "term/terms";
	}

	@RequestMapping(value = "/edit-term", method = RequestMethod.GET)
	protected ModelAndView editTermGet(@ModelAttribute("termId") String termId) {
		Term term = termService.getByTermId(Integer.parseInt(termId));
		ModelAndView model = new ModelAndView("term/editTerm", "TermModel", term);
		return model;
	}

	@RequestMapping(value = "/update-term", method = RequestMethod.POST)
	protected String editTermPost(@ModelAttribute("TermModel") Term term, Model model) {
		termService.update(term);
		return "redirect:/terms/terms";
	}

	@RequestMapping(value = "/delete-term", method = RequestMethod.POST)
	protected String deleteTermPost(@ModelAttribute("termId") String termId, Model model) {
		termService.delete(termService.getByTermId(Integer.parseInt(termId)));
		return "redirect:/terms/terms";
		//model.addAttribute("terms", termService.getAll());
		//return "term/terms";
	}
}
