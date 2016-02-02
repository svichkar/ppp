package com.nixsolutions.controllers;

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
import com.nixsolutions.service.BookService;

@Controller
@RequestMapping("/findbook")
public class FindBookController {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	BookService bookService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String retrieveBooks(Model model){
		return "FindBook";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String searchBooks(
			@RequestParam("search criteria")String searchCriteria,
			@RequestParam("search input")String input,
			Model model){
	LOG.entry(searchCriteria, model);
		
		List<Book> allBooks = null;

		switch (searchCriteria) {
		case "all":
			allBooks = bookService.getAllBooks();
			break;
		case "name":
			allBooks = bookService.getBooksByName(input);
			break;
		case "author":
			allBooks = bookService.getBooksByAuthor(input);
			break;
		case "category":
			allBooks = bookService.getBooksByCategory(input);
			model.addAttribute("allBooks", allBooks);
			break;
		default:
			break;
		}

		model.addAttribute("allBooks", allBooks);	
		
		return "FindBook";
	}
}
