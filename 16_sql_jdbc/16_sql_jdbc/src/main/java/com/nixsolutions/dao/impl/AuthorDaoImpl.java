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

import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.H2ConnManager;
import com.nixsolutions.entity.Author;

public class AuthorDaoImpl implements AuthorDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Author> getAllAuthors() {
		LOG.entry();
		String sql = "SELECT * FROM author;";
		List<Author> authors = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection();
				Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				Author auth = new Author();
				auth.setAuthorId(result.getInt("author_id"));
				auth.setFirstName(result.getString("first_name"));
				auth.setSecondName(result.getString("last_name"));
				authors.add(auth);
			}
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all authors", e));
		}
		return LOG.exit(authors);
	}

	@Override
	public Author getAuthorById(int authorId) {
		LOG.entry(authorId);
		String sql = "SELECT * FROM author WHERE author_id = ?;";
		Author author = null;
		try (Connection conn = H2ConnManager.getConnection();
				PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, authorId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				author = new Author();
				author.setAuthorId(result.getInt("author_id"));
				author.setFirstName(result.getString("first_name"));
				author.setSecondName(result.getString("last_name"));
			}
		} catch (SQLException e) {
			LOG.throwing(
					new DaoException("not able to get an author by Id", e));
		}
		return LOG.exit(author);
	}

	@Override
	public void createAuthor(Author author) {
		LOG.entry(author);
		String sql = "INSERT INTO author (first_name, last_name) VALUES (?, ?)";
		try (Connection conn = H2ConnManager.getConnection();
				PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, author.getFirstName());
			statem.setString(2, author.getSecondName());
			statem.executeUpdate();
			LOG.exit("author was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create an author", e));
		}
	}

	@Override
	public void updateAuthor(Author author) {
		LOG.entry(author);
		String sql = "UPDATE author SET first_name = ?, last_name = ?  WHERE author_id = ?";
		try (Connection conn = H2ConnManager.getConnection();
				PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, author.getFirstName());
			statem.setString(2, author.getSecondName());
			statem.setLong(3, author.getAuthorId());
			statem.executeUpdate();
			LOG.exit("author was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to update the author", e));
		}
	}

	@Override
	public void deleteAuthor(Author author) {
		LOG.entry(author);
		String sql = "DELETE FROM author WHERE author_id = ?";
		try (Connection conn = H2ConnManager.getConnection();
				PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, author.getAuthorId());
			statem.executeUpdate();
			LOG.exit("author was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to delete the author", e));
		}
	}
}
