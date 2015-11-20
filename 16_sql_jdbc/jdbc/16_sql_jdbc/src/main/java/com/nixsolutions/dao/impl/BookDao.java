package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.AbstractDao;
import com.nixsolutions.entity.Book;

public class BookDao extends AbstractDao {

	public List<Book> getBookById(int id) {
		return super.find(null, "id=" + id);

	}
}
