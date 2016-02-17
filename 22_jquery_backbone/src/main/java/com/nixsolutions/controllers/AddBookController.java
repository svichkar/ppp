package com.nixsolutions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entity.Category;
import com.nixsolutions.entity.Cell;
import com.nixsolutions.service.BookService;
import com.nixsolutions.service.CategoryService;
import com.nixsolutions.service.CellService;

@Controller
@RequestMapping("/addbook")
public class AddBookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private CellService cellService;
	@Autowired
	private CategoryService categoryService;
	
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
	
			bookService.createBook(bookName, authorFirstName, authorLastName, cell, category, count);
		
			return "redirect:/addbook";
	}
}
