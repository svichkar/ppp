package dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.BookDao;
import dao.CategoryDao;
import entity.Book;
import entity.Category;

public class BookDaoImpl extends AbstractDaoImpl implements BookDao {

	@Override
	public List<Book> getAllBooks() {
		return super.find(null, null);
	}

	@Override
	public List<Book> addCategoryNameToBookObject(List<Book> books,
			List<Category> cat) {
		for (Book book : books) {
			for (Category category : cat) {
				if (book.getCategory_id() == category.getId()) {
					book.setCategory_name(category.getName());
				}

			}

		}
		return books;
	}

	@Override
	public boolean addNewBook(Book book) {

		return super.addNewRow(
				Arrays.asList("name", "author", "publisher", "category_id"),
				Arrays.asList(book.getName(), book.getAuthor(),
						book.getPublisher(),
						new Integer(book.getCategory_id()).toString()));
	}

	@Override
	public List<Book> searchByAllFields(String searchQuery) {

		return super.find(null,
				"NAME LIKE '%" + searchQuery + "%' OR AUTHOR LIKE '%"
						+ searchQuery + "%' OR PUBLISHER LIKE '%" + searchQuery
						+ "%'");
	}

	@Override
	public List<Book> searchByBookName(String searchQuery) {

		return super.find(null, "NAME LIKE '%" + searchQuery + "%'");
	}

	@Override
	public List<Book> searchByBookAuthor(String searchQuery) {
		return super.find(null, "AUTHOR LIKE '%" + searchQuery + "%'");
	}

	@Override
	public List<Book> searchByBookPublisher(String searchQuery) {
		return super.find(null, "PUBLISHER LIKE '%" + searchQuery + "%'");
	}

	@Override
	public List<Book> searchByBookCategory(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> getBooksListByCategoryName(String categoryName) {
		try {
			DBDaoFactory factory = new DBDaoFactory();
			CategoryDao cf = factory.getCategoryDao();
			List<Category> category = cf.searchCategoryByName(categoryName);
			int requiredCategory = category.get(0).getId();
			String fullCategoryName = category.get(0).getName();
			List<Book> books = super.find(null,
					"category_id=" + requiredCategory);
			for (Book book : books) {
				book.setCategory_name(fullCategoryName);

			}

			return books;
		} catch (Exception e) {

			return new ArrayList<Book>();
		}
	}

	@Override
	public boolean updateBook(Book book) {
		if (new Integer(book.getId()) == null) {
			return false;
		}
		return super.update(
				Arrays.asList("name", "author", "publisher", "category_id"),
				Arrays.asList(book.getName(), book.getAuthor(),
						book.getPublisher(),
						new Integer(book.getCategory_id()).toString()),
				"id=" + book.getId());
	}

	@Override
	public boolean deleteById(String id) {
	
		return super.delete("id=" + id);
	}
	

}
