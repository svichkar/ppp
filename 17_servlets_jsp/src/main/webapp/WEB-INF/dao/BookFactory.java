package dao;

import java.util.List;

import dao.impl.BookDao;
import entity.Book;

public class BookFactory implements CommonFactory {
	private BookDao bookDao;
	public BookFactory() {
		bookDao = new BookDao();
	}

	public boolean addNewRow(List<String> columns, List<String> elements) {
		return bookDao.addNewRow(columns, elements);

	}

	public boolean update(List<String> columns, List<String> elements,
			String whereState) {

		return bookDao.update(columns, elements, whereState);
	}

	public boolean delete(String whereState) {
		return bookDao.delete(whereState);
	}

	public List<Book> find(String[] searchField, String searchQuery) {
		return bookDao.find(searchField, searchQuery);
	}

	public List<Book> getBookById(int id) {
		return bookDao.getBookById(id);
	}

}
