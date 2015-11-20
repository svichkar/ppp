package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.dao.impl.BookInstanceDao;
import com.nixsolutions.entity.BookInstance;

public class BookInstanceFactory implements CommonFactory {
	private BookInstanceDao biDao;
	public BookInstanceFactory() {
		biDao = new BookInstanceDao();
	}

	@Override
	public boolean addNewRow(List<String> columns, List<String> elements) {
		return biDao.addNewRow(columns, elements);
	}

	@Override
	public boolean update(List<String> columns, List<String> elements,
			String whereState) {
		return biDao.update(columns, elements, whereState);
	}

	@Override
	public boolean delete(String whereState) {
		return biDao.delete(whereState);
	}

	@Override
	public List<BookInstance> find(String[] searchField, String searchQuery) {
		return biDao.find(searchField, searchQuery);

	}

}
