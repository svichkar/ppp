package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nixsolutions.studentgrade.webservice.rest.TermServiceWeb;

@Controller
public class BackboneTermsController {

	@Autowired
	TermServiceWeb termServiceWeb;
	
	@RequestMapping(value = "/backboneTerms")
	protected String backboneTerms (Model model) {
		return "backboneTerms";
	}

}
