package com.nixsolutions.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entity.Client;
import com.nixsolutions.service.ClientService;

@Controller
@RequestMapping("/addreader")
public class AddReaderController {
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String retrieveCategory(Model model){
		return "AddReader";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String searchBooks(
			@RequestParam("readerfirstname")String firstName,
			@RequestParam("readerlastname")String lastName,
			@RequestParam("email")String email,
			Model model){
	LOG.entry(firstName, lastName, email);
	
	Client client = new Client();
	client.setFirstName(firstName);
	client.setSecondName(lastName);
	client.setEmail(email);
	clientService.createClient(client);	
		return "AddReader";
	}
}
