package com.nixsolutions.controllers;

import java.util.ArrayList;
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
import com.nixsolutions.service.BookService;
import com.nixsolutions.service.ClientService;

@Controller
@RequestMapping("/findreader")
public class FindReaderController {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	ClientService clientService;
	@Autowired
	BookService bookService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String retrieveBooks(Model model){
		return "FindReader";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String searchBooks(
			@RequestParam(value = "button", required = false)String button,
			@RequestParam(value = "search input", required = false)String searchInput,
			@RequestParam(value = "loaned", required = false)String[] booksIds,
			Model model){
		
		LOG.debug("entered the method");
		List<Client> readers = new ArrayList<>();

		// loaned book list section
		List<Book> toBeloaned = new ArrayList<>();
		if (booksIds != null) {
			for (String bookId : booksIds) {
				toBeloaned.add(bookService.getBookById(Long.valueOf(bookId)));
			}
			model.addAttribute("toBeloaned", toBeloaned);
		}

		if ("search".equals(button)) {
			readers = clientService.getClientsByName(searchInput);
			model.addAttribute("readers", readers);
		}	
		return "FindReader";
	}
	
	/*
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		processFindReaders(request, response);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindReader.jsp");
		rd.forward(request, response);
	}

	private void processFindReaders(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("entered the method");
		String[] booksIds = request.getParameterValues("loaned");
		List<Client> readers = new ArrayList<>();

		// loaned book list section
		List<Book> toBeloaned = new ArrayList<>();
		if (booksIds != null) {
			for (String bookId : booksIds) {
				toBeloaned.add(factory.getBookDao().getBookById(Long.valueOf(bookId)));
			}
			request.setAttribute("toBeloaned", toBeloaned);
		}

		if ("search".equals(request.getParameter("button"))) {
			readers = factory.getClientDao().getClientsByName(request.getParameter("search input"));
			request.setAttribute("readers", readers);
		}
	}*/
}