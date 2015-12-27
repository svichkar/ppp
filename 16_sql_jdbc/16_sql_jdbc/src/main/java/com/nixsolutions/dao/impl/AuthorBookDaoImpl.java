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
import com.nixsolutions.dao.AuthorBookDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.AuthorBook;

public class AuthorBookDaoImpl implements AuthorBookDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public AuthorBook getAuthorBookById(int authorId, int bookId) {
		String sql = "SELECT * FROM author_book WHERE author_id = ? and book_id = ?;";
		AuthorBook authorBook = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorId);
			statem.setInt(2, bookId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				authorBook = new AuthorBook(result.getInt("author_id"), result.getInt("book_id"));
			}
			LOG.trace("the authorBook was retrieved");
		} catch (SQLException e) {
			LOG.error("not able to get a authorBook by Id", e);
			LOG.throwing(new DaoException("not able to get a authorBook by Id"));
		}
		return authorBook;
	}

	@Override
	public List<AuthorBook> getAllAuthorBook() {
		String sql = "SELECT * FROM author_book;";
		List<AuthorBook> authorBooks = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				AuthorBook authBook = new AuthorBook(result.getInt("author_id"), result.getInt("book_id"));
				authorBooks.add(authBook);
				// need to insert assertion - if transaction was done
			}
			LOG.trace("all the authorBooks were retrieved");
		} catch (SQLException e) {
			LOG.error("not able to get authorBooks", e);
			LOG.throwing(new DaoException("not able to get all authorBooks"));
		}
		return authorBooks;
	}

	@Override
	public List<AuthorBook> getBooksIdByAuthorId(int authorId) {
		String sql = "SELECT * FROM author_book WHERE author_id = ?;";
		List<AuthorBook> authorBooks = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorId);
			ResultSet result = statem.executeQuery();
			while (result.next()) {
				AuthorBook authBook = new AuthorBook(result.getInt("author_id"), result.getInt("book_id"));
				authorBooks.add(authBook);
				// need to insert assertion - if transaction was done
			}
			LOG.trace("all the authorBooks were retrieved");
		} catch (SQLException e) {
			LOG.error("not able to get authorBooks", e);
			LOG.throwing(new DaoException("not able to get all authorBooks"));
		}
		return authorBooks;
	}

	@Override
	public void createAuthorBook(AuthorBook authorBook) {
		String sql = "INSERT INTO author_book (author_id, book_id) VALUES (?, ?)";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorBook.getAuthorId());
			statem.setInt(2, authorBook.getBookId());
			statem.executeUpdate();
			LOG.trace("authorBook entry was created");
			// need to insert assertion - if transaction was done
		} catch (SQLException e) {
			LOG.error("not able to create the authorBook", e);
			LOG.throwing(new DaoException("not able to create the authorBook"));
		}
	}

	@Override
	public void updateAuthorBook(AuthorBook authorBook) {
		String sql = "UPDATE author_book SET author_id = ?, book_id = ? WHERE author_id = ? AND book_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorBook.getAuthorId());
			statem.setInt(2, authorBook.getBookId());
			statem.setInt(1, authorBook.getAuthorId());
			statem.setInt(2, authorBook.getBookId());
			statem.executeUpdate();
			LOG.trace("authorBook was updated");
			// need to insert assertion - if transaction was done
		} catch (SQLException e) {
			LOG.error("not able to update the authorBook", e);
			LOG.throwing(new DaoException("not able to update the authorBook"));
		}
	}

	@Override
	public void deleteAuthorBook(AuthorBook authorBook) {
		String sql = "DELETE FROM author_book WHERE author_id = ? AND book_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorBook.getAuthorId());
			statem.setInt(2, authorBook.getBookId());
			statem.executeUpdate();
			// need to insert assertion - if transaction was done
		} catch (SQLException e) {
			LOG.error("not able to delete the authorBook", e);
			LOG.throwing(new DaoException("not able to delete the authorBook"));
		}

	}

}
