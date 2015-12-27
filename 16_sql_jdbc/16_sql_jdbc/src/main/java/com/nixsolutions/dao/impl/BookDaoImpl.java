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

import com.nixsolutions.app.H2ConnManager;
import com.nixsolutions.dao.BookDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Book;

public class BookDaoImpl implements BookDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Book> getAllBooks() {
		String sql = "SELECT * FROM book;";
		List<Book> books = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				Book book = new Book();
				book.setBookId(result.getInt("book_id"));
				book.setName(result.getString("name"));
				book.setCategoryId(result.getInt("category_id"));
				book.setCellId(result.getInt("cell_id"));
				books.add(book);
			}
			LOG.trace("all the books were retrieved");
		} catch (SQLException e) {
			LOG.error("not able to get books", e);
			LOG.throwing(new DaoException("not able to get all books"));
		}
		return books;
	}

	@Override
	public Book getBookById(int bookId) {
		String sql = "SELECT * FROM book WHERE book_id = ?;";
		Book book = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, bookId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				book = new Book();
				book.setBookId(result.getInt("book_id"));
				book.setName(result.getString("name"));
				book.setCategoryId(result.getInt("category_id"));
				book.setCellId(result.getInt("cell_id"));
			}
			LOG.trace("the book was retrieved");
		} catch (SQLException e) {
			LOG.error("not able to get a book by Id", e);
			LOG.throwing(new DaoException("not able to get a book by Id"));
		}
		return book;
	}

	@Override
	public void createBook(Book book) {
		String sql = "INSERT INTO book (name, category_id, cell_id) VALUES (?, ?, ?);";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, book.getName());
			statem.setInt(2, book.getCategoryId());
			statem.setInt(3, book.getCellId());
			statem.executeUpdate();
			LOG.trace("book was created");
		} catch (SQLException e) {
			LOG.error("not able to create an book", e);
			LOG.throwing(new DaoException("not able to create an book"));
		}
	}

	@Override
	public void updateBook(Book book) {
		String sql = "UPDATE book SET name = ?, category_id = ?, cell_id=?  WHERE book_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, book.getName());
			statem.setInt(2, book.getCategoryId());
			statem.setInt(3, book.getCellId());
			statem.setLong(4, book.getBookId());
			statem.executeUpdate();
			LOG.trace("book was updated");
		} catch (SQLException e) {
			LOG.error("not able to update the book", e);
			LOG.throwing(new DaoException("not able to update the book"));
		}
	}

	@Override
	public void deleteBook(Book book) {
		String sql = "DELETE FROM book WHERE book_id = ?;";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, book.getBookId());
			statem.executeUpdate();
		} catch (SQLException e) {
			LOG.error("not able to delete the book", e);
			LOG.throwing(new DaoException("not able to delete the book"));

		}
	}
}
