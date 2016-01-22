package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.BookDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.H2ConnManager;
import com.nixsolutions.entity.Book;

public class BookDaoImpl implements BookDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Book> getAllBooks() {
		LOG.entry();
		String sql = "SELECT * FROM book;";
		List<Book> books = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				Book book = new Book();
				book.setBookId(result.getLong("book_id"));
				book.setName(result.getString("name"));
				book.setCategoryId(result.getLong("category_id"));
				book.setCellId(result.getLong("cell_id"));
				book.setCount(result.getInt("count"));
				books.add(book);
			}
			LOG.trace("all the books were retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all books", e));
		}
		return LOG.exit(books);
	}

	@Override
	public Book getBookById(Long bookId) {
		LOG.entry(bookId);
		String sql = "SELECT * FROM book WHERE book_id = ?;";
		Book book = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, bookId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				book = new Book();
				book.setBookId(result.getLong("book_id"));
				book.setName(result.getString("name"));
				book.setCategoryId(result.getLong("category_id"));
				book.setCellId(result.getLong("cell_id"));
				book.setCount(result.getInt("count"));
			}
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a book by Id", e));
		}
		return LOG.exit(book);
	}

	@Override
	public Book createBook(Book book) {
		LOG.entry(book);
		String sql = "INSERT INTO book (name, category_id, cell_id, count) VALUES (?, ?, ?, ?);";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statem.setString(1, book.getName());
			statem.setLong(2, book.getCategoryId());
			statem.setLong(3, book.getCellId());
			statem.setLong(4, book.getCount());
			statem.executeUpdate();
			ResultSet generatedKeys = statem.getGeneratedKeys();
			 if (generatedKeys.next()) {
				 book.setBookId(generatedKeys.getLong(1));
	            } else {
	            	LOG.throwing(new DaoException("Creating book failed, no ID obtained."));
	            }
			LOG.exit("book was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create an book", e));
		}
		return book;
	}

	@Override
	public void updateBook(Book book) {
		LOG.entry(book);
		String sql = "UPDATE book SET name = ?, category_id = ?, cell_id=?, count=?  WHERE book_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, book.getName());
			statem.setLong(2, book.getCategoryId());
			statem.setLong(3, book.getCellId());
			statem.setInt(4, book.getCount());
			statem.setLong(5, book.getBookId());
			statem.executeUpdate();
			LOG.exit("book was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to update the book", e));
		}
	}

	@Override
	public void deleteBook(Book book) {
		LOG.entry(book);
		String sql = "DELETE FROM book WHERE book_id = ?;";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, book.getBookId());
			statem.executeUpdate();
			LOG.exit("book was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to delete the book", e));

		}
	}

	@Override
	public List<Book> getBooksByAuthor(String name) {
		LOG.entry(name);
		String sql = "SELECT * FROM author_book AS ab INNER JOIN author AS a  "
				+ "ON ab.author_id = a.author_id and a.last_name = ? "
				+ "INNER JOIN book as b ON ab.book_id = b.book_id;";
		List<Book> books = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, name);
			
			ResultSet result = statem.executeQuery();
			while (result.next()) {
				Book book = new Book();
				book.setBookId(result.getLong("book_id"));
				book.setName(result.getString("name"));
				book.setCategoryId(result.getLong("category_id"));
				book.setCellId(result.getLong("cell_id"));
				book.setCount(result.getInt("count"));
				books.add(book);
			}
			LOG.trace("all the books were retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all books", e));
		}
		return LOG.exit(books);
	}

	@Override
	public List<Book> getBooksByCategory(String name) {
		LOG.entry(name);
		String sql = "SELECT * FROM book AS b "
				+ "INNER JOIN category AS c ON b.category_id = c.category_id "
				+ "WHERE c.name = ?;";
		List<Book> books = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, name);
			
			ResultSet result = statem.executeQuery();
			while (result.next()) {
				Book book = new Book();
				book.setBookId(result.getLong("book_id"));
				book.setName(result.getString("name"));
				book.setCategoryId(result.getLong("category_id"));
				book.setCellId(result.getLong("cell_id"));
				book.setCount(result.getInt("count"));
				books.add(book);
			}
			LOG.trace("all the books were retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all books", e));
		}
		return LOG.exit(books);
	}

	@Override
	public List<Book> getBooksByName(String name) {
		LOG.entry(name);
		name = "%"+name+"%";
		String sql = "SELECT * FROM book WHERE name LIKE ?;";
		List<Book> books = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, name);
			
			ResultSet result = statem.executeQuery();
			while (result.next()) {
				Book book = new Book();
				book.setBookId(result.getLong("book_id"));
				book.setName(result.getString("name"));
				book.setCategoryId(result.getLong("category_id"));
				book.setCellId(result.getLong("cell_id"));
				book.setCount(result.getInt("count"));
				books.add(book);
			}
			LOG.trace("all the books were retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all books", e));
		}
		return LOG.exit(books);
	}
}
