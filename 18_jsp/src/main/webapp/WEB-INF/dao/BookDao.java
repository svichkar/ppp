package dao;

import java.util.List;

import entity.Book;
import entity.Category;

public interface BookDao {

	public boolean addNewRow(List<String> columns, List<String> elements);
//	public boolean update(List<String> columns, List<String> elements,
//			String whereState);
	public boolean updateBook(Book book);
	public boolean delete(String whereState);
	public List find(String[] searchField, String searchQuery);
	public boolean addNewBook(Book book);
	public List<Book> getAllBooks();
	public List<Book> addCategoryNameToBookObject(List<Book> books, List<Category> cat);
	public List<Book> searchByAllFields(String searchQuery);
	public List<Book> searchByBookName(String searchQuery);
	public List<Book> searchByBookAuthor(String searchQuery);
	public List<Book> searchByBookPublisher(String searchQuery);
	public List<Book> searchByBookCategory(String searchQuery);
	public List<Book> getBooksListByCategoryName(String categoryName);
	public boolean deleteById(String id);
}
