package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Category;

public interface CategoryService {
	 List<Category> getAllCategories();
	 Category getCategoryById(Long categoryId);
	 Category getCategoryByName(String categoryName);
	 void createCategory(Category category);
	 void updateCategory(Category category);
	 void deleteCategory(Category category);
}

