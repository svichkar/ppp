package dao.impl;

import java.util.List;

import dao.AbstractDao;
import entity.Book;

public class BookDao extends AbstractDao {

	public List<Book> getBookById(int id) {
		return super.find(null, "id=" + id);

	}
}
