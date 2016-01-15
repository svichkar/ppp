package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Category;

public interface CategoryDao {
	public List<Category> getAllCategories();
	public Category getCategoryById(int categoryId);
	public Category getCategoryByName(String categoryName);
	public void createCategory(Category category);
	public void updateCategory(Category category);
	public void deleteCategory(Category category);
}

