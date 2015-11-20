package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.dao.impl.CategoryDao;
import com.nixsolutions.entity.Category;

public class CategoryFactory implements CommonFactory {
	private CategoryDao catDao;
	public CategoryFactory() {
		catDao = new CategoryDao();
	}

	@Override
	public boolean addNewRow(List<String> columns, List<String> elements) {
		return catDao.addNewRow(columns, elements);
	}

	@Override
	public boolean update(List<String> columns, List<String> elements,
			String whereState) {
		return catDao.addNewRow(columns, elements);
	}

	@Override
	public boolean delete(String whereState) {
		return catDao.delete(whereState);
	}

	@Override
	public List<Category> find(String[] searchField, String searchQuery) {
		return catDao.find(searchField, searchQuery);
	}

}
