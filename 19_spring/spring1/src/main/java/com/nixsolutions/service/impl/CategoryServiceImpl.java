package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CategoryDao;
import com.nixsolutions.entity.Category;
import com.nixsolutions.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		return categoryDao.getCategoryByName(categoryName);
	}

	@Override
	public void createCategory(Category category) {
		categoryDao.createCategory(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryDao.deleteCategory(category);
	}

}
