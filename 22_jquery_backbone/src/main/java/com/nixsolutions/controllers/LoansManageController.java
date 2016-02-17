package com.nixsolutions.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Client;
import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.service.BookService;
import com.nixsolutions.service.ClientService;
import com.nixsolutions.service.RentJournalService;

@Controller
@RequestMapping("/loansmanage")
public class LoansManageController {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	RentJournalService rentJournalService;
	@Autowired
	ClientService clientService;
	@Autowired
	BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String retrieveLoans(Model model) {
		return "ManageLoans";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String editLoans(@RequestParam(value = "loaned", required = false) String[] booksIds,
			@RequestParam(value = "selectbook", required = false) String[] selectedBooks,
			@RequestParam(value = "book returned", required = false) String[] returnedBooks,
			@RequestParam(value = "current client", required = false) String curClient,
			Model model) {
		LOG.entry(">>>" + booksIds, selectedBooks, returnedBooks);
		Client reader = clientService.getClientById(Long.valueOf(curClient));
		// submit a new book to the reader
		LOG.debug(">>>>>>>>>>>>>books to be added to loan " + selectedBooks);
		if (selectedBooks != null) {
			rentJournalService.submitBooks(selectedBooks, reader);
		}
		// reader returned books - update rent
		if (returnedBooks != null) {
			rentJournalService.returnedBooks(returnedBooks);
		}
		reader = clientService.getClientById(reader.getClientId());
		model.addAttribute("reader", reader);
		model.addAttribute("toBeloaned", rentJournalService.checkBooksInList(booksIds));

		return "ManageLoans";
	}

	
}