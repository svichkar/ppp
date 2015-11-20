package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.dao.impl.JournalDao;
import com.nixsolutions.entity.Journal;

public class JournalFactory implements CommonFactory {
	private JournalDao jDao;
	public JournalFactory() {
		jDao = new JournalDao();
	}
	@Override
	public boolean addNewRow(List<String> columns, List<String> elements) {
		return jDao.addNewRow(columns, elements);
	}

	@Override
	public boolean update(List<String> columns, List<String> elements,
			String whereState) {
		return jDao.update(columns, elements, whereState);
	}

	@Override
	public boolean delete(String whereState) {
		return jDao.delete(whereState);
	}

	@Override
	public List<Journal> find(String[] searchField, String searchQuery) {
		return jDao.find(searchField, searchQuery);
	}

}
