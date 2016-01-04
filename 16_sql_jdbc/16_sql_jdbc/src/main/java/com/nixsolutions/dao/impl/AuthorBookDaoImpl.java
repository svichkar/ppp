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
		LOG.entry(authorId, bookId);
		String sql = "SELECT * FROM author_book WHERE author_id = ? and book_id = ?;";
		AuthorBook authorBook = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorId);
			statem.setInt(2, bookId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				authorBook = new AuthorBook(result.getInt("author_id"), result.getInt("book_id"));
			}
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a authorBook by Id", e));
		}
		return LOG.exit(authorBook);
	}

	@Override
	public List<AuthorBook> getAllAuthorBook() {
		LOG.entry();
		String sql = "SELECT * FROM author_book;";
		List<AuthorBook> authorBooks = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				AuthorBook authBook = new AuthorBook(result.getInt("author_id"), result.getInt("book_id"));
				authorBooks.add(authBook);
			}
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get authorBooks", e));
		}
		return LOG.exit(authorBooks);
	}

	@Override
	public List<AuthorBook> getBooksIdByAuthorId(int authorId) {
		LOG.entry(authorId);
		String sql = "SELECT * FROM author_book WHERE author_id = ?;";
		List<AuthorBook> authorBooks = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorId);
			ResultSet result = statem.executeQuery();
			while (result.next()) {
				AuthorBook authBook = new AuthorBook(result.getInt("author_id"), result.getInt("book_id"));
				authorBooks.add(authBook);
			}
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all authorBooks", e));
		}
		return LOG.exit(authorBooks);
	}

	@Override
	public void createAuthorBook(AuthorBook authorBook) {
		LOG.entry(authorBook);
		String sql = "INSERT INTO author_book (author_id, book_id) VALUES (?, ?)";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorBook.getAuthorId());
			statem.setInt(2, authorBook.getBookId());
			statem.executeUpdate();
			LOG.exit("authorBook entry was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create the authorBook", e));
		}
	}

	@Override
	public void updateAuthorBook(AuthorBook authorBook) {
		LOG.entry(authorBook);
		String sql = "UPDATE author_book SET author_id = ?, book_id = ? WHERE author_id = ? AND book_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorBook.getAuthorId());
			statem.setInt(2, authorBook.getBookId());
			statem.setInt(1, authorBook.getAuthorId());
			statem.setInt(2, authorBook.getBookId());
			statem.executeUpdate();
			LOG.exit("authorBook was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to update the authorBook", e));
		}
	}

	@Override
	public void deleteAuthorBook(AuthorBook authorBook) {
		LOG.entry(authorBook);
		String sql = "DELETE FROM author_book WHERE author_id = ? AND book_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorBook.getAuthorId());
			statem.setInt(2, authorBook.getBookId());
			statem.executeUpdate();
			LOG.exit("authorBook was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to delete the authorBook", e));
		}

	}

}
