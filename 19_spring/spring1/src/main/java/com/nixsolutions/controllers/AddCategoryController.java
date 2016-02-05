package com.nixsolutions.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entity.Category;
import com.nixsolutions.service.CategoryService;

@Controller
@RequestMapping("/addcategory")
public class AddCategoryController {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String retrieveCategory(Model model){
		return "AddCategory";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String searchBooks(
			@RequestParam("categoryname")String categoryName,
			Model model){
	LOG.entry(categoryName);
		
	if (categoryService.getCategoryByName(categoryName.trim()) == null) {
		categoryService.createCategory(new Category(categoryName.trim()));
		model.addAttribute("status", "true");
	} else {
		model.addAttribute("status", "false");
	}
		
		return "AddCategory";
	}
}
