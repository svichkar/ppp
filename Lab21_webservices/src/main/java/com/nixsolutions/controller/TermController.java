package com.nixsolutions.controller;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.entity.Term;
import com.nixsolutions.service.TermService;
import com.nixsolutions.service.jersey.TermServiceJersey;

@Controller
@RequestMapping(value = "/terms")
public class TermController {
	@Autowired
	private TermServiceJersey termService;
	
	private Client client = ClientBuilder.newClient();
	private static final String SERVICE_URL = "http://localhost:8080/Lab21_webservices/services/term";

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
		/*Response response = client.target(SERVICE_URL)
				.path("/getByName")
				.queryParam("alias", alias)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		Term term = response.readEntity(Term.class);*/
		Term term = new Term();
		term.setAlias(alias);
		Entity term2 = Entity.entity(term, MediaType.APPLICATION_XML);
		
		client.target(SERVICE_URL)
		.path("/add")
		//.resolveTemplate("term", term)
		.request()
		.post(Entity.entity(term, MediaType.APPLICATION_XML));
		//termService.create(term);
		
		model.addAttribute("terms", termService.getAll());
		return "/WEB-INF/jsp/term/Terms.jsp";
	}

	@RequestMapping(value = "/editTerm.do", method = RequestMethod.GET)
	protected String editTermGet(@ModelAttribute("termId") String termId, Model model) {
		Response response = client.target(SERVICE_URL)
				.path("/getById")
				.queryParam("termId", termId)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		Term term = response.readEntity(Term.class);
		//Term term = termService.getByTermId(Integer.parseInt(termId));
		model.addAttribute("term", term);
		return "/WEB-INF/jsp/term/EditTerm.jsp";
	}

	@RequestMapping(value = "/editTerm.do", method = RequestMethod.POST)
	protected String editTermPost(@ModelAttribute("termId") String termId,
			@ModelAttribute("alias") String alias, Model model) {
		Response response = client.target(SERVICE_URL)
				.path("/getById")
				.queryParam("termId", termId)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		Term term = response.readEntity(Term.class);
		//Term term = termService.getByTermId(Integer.parseInt(termId));
		term.setAlias(alias);
		client.target(SERVICE_URL)
		.path("/update")
		.resolveTemplate("term", term)
		.request()
		.post(Entity.entity(term, MediaType.APPLICATION_XML));
		
		//termService.update(term);
		Response responseAll = client.target(SERVICE_URL)
				.path("/getAll")
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		//List<Term> terms = response.(Term.class);
		model.addAttribute("terms", termService.getAll());
		return "/WEB-INF/jsp/term/Terms.jsp";
	}

	@RequestMapping(value = "/deleteTerm.do", method = RequestMethod.POST)
	protected String deleteTermPost(@ModelAttribute("termId") String termId, Model model) {
		Response response = client.target(SERVICE_URL)
				.path("/getById")
				.queryParam("termId", termId)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		Term term = response.readEntity(Term.class);
		
		client.target(SERVICE_URL)
		.path("/delete")
		.resolveTemplate("term", term)
		.request()
		.post(Entity.entity(term, MediaType.APPLICATION_XML));
		
		//termService.delete(termService.getByTermId(Integer.parseInt(termId)));
		model.addAttribute("terms", termService.getAll());
		return "/WEB-INF/jsp/term/Terms.jsp";
	}
}
