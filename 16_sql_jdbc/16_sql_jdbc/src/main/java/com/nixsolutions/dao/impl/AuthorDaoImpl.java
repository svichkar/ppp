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
import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.entity.Author;

public class AuthorDaoImpl implements AuthorDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Author> getAllAuthors() {
		String sql = "SELECT * FROM AUTHOR;";
		List<Author> author = new ArrayList<>();

		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {

			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				Author auth = new Author();
				auth.setAuthorId(result.getInt("author_id"));
				auth.setFirstName(result.getString("first_name"));
				auth.setSecondName(result.getString("last_name"));
				author.add(auth);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return author;
	}

	@Override
	public Author getAuthor(int authorId) {
		String sql = "SELECT * FROM AUTHOR WHERE author_id = ?;";
		Author author = new Author();

		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {

			statem.setInt(1, authorId);

			ResultSet result = statem.executeQuery();
			result.next();
			author.setAuthorId(result.getInt("author_id"));
			author.setFirstName(result.getString("first_name"));
			author.setSecondName(result.getString("last_name"));

			// Author author = new Author(result.getInt("author_id"),
			// result.getString("first_name"), result.getString("second_name"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return author;
	}

	@Override
	public void createAuthor(Author author) {
		String sql = "INSERT INTO author (first_name, last_name) VALUES (?, ?)";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {

			statem.setString(1, author.getFirstName());
			statem.setString(2, author.getSecondName());
			
			statem.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAuthor(Author author) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAuthor(Author author) {

	}

}
