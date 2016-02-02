package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Category;

public interface CategoryService {
	public List<Category> getAllCategories();
	public Category getCategoryById(Long categoryId);
	public Category getCategoryByName(String categoryName);
	public void createCategory(Category category);
	public void updateCategory(Category category);
	public void deleteCategory(Category category);
}

