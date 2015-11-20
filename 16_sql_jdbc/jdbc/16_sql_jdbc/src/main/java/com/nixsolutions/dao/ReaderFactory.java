package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.dao.impl.ReaderDao;
import com.nixsolutions.entity.Reader;

public class ReaderFactory implements CommonFactory {
	private ReaderDao rDao;
	public ReaderFactory() {
		rDao = new ReaderDao();

	}
	@Override
	public boolean addNewRow(List<String> columns, List<String> elements) {
		return rDao.addNewRow(columns, elements);
	}
	@Override
	public boolean update(List<String> columns, List<String> elements,
			String whereState) {
		return rDao.update(columns, elements, whereState);
	}
	@Override
	public boolean delete(String whereState) {
		return rDao.delete(whereState);
	}
	@Override
	public List<Reader> find(String[] searchField, String searchQuery) {
		return rDao.find(searchField, searchQuery);
	}

}
