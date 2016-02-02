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

import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Category;
import com.nixsolutions.entity.Cell;
import com.nixsolutions.service.AuthorService;
import com.nixsolutions.service.BookService;
import com.nixsolutions.service.CategoryService;
import com.nixsolutions.service.CellService;

@Controller
@RequestMapping("/addbook")
public class AddBookController {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private BookService bookService;
	@Autowired
	private CellService cellService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String retrieveBook(Model model){
		List<Cell> cells = cellService.getAllCells();
		List<Category> categories = categoryService.getAllCategories();

		model.addAttribute("cells", cells);
		model.addAttribute("categories", categories);
		return "AddBook";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String createBook(
			@RequestParam("bookname")String bookName,
			@RequestParam("authorfirstname")String authorFirstName,
			@RequestParam("authorlastname")String authorLastName,
			@RequestParam("selectcell")String cell,
			@RequestParam("selectcategory")String category,
			@RequestParam("count")String count,
			Model model){
	LOG.entry(bookName, authorFirstName, authorLastName, cell, category, count);
	
			// new book
			Book book = new Book();
			book.setName(bookName);
			book.setCell(cellService.getCellByName(cell));
			book.setCategory(categoryService.getCategoryByName(category));
			book.setCount(Integer.valueOf(count));

			// new or existing author
			Author auth = authorService.getAuthorByName(authorLastName);
			List<Author> authors = new ArrayList<>();
			if (auth != null) {
				LOG.debug("we have such an author");
				authors.add(auth);
				book.setAuthors(authors);
			} else {
				auth = new Author();
				auth.setFirstName(authorFirstName);
				auth.setSecondName(authorLastName);
				authorService.createAuthor(auth);
				LOG.debug("during creation of new book the author was created: " + auth);
				authors.add(auth);
				book.setAuthors(authors);
			}
			bookService.createBook(book);
		
			return "AddBook";
	}
}
