package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.AuthorDAO;
import com.nixsolutions.library.entity.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class AuthorDaoImpl implements AuthorDAO {
    public static Logger LOGGER = LogManager.getLogger(AuthorDaoImpl.class.getName());

    @Override
    public Author create(Author entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO author (first_name, last_name) VALUES ('" + entity.getFirstName() +
                    "' ,'" + entity.getLastName() + "');");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void update(Author entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE author SET first_name='" + entity.getFirstName() + "', last_name='" +
                    entity.getLastName() + "' WHERE author_id='" + entity.getAuthorId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Author entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM author WHERE author_id='" + entity.getAuthorId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Author findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author WHERE author_id = '" + id + "';");
            resultSet.next();
            Author entity = new Author(resultSet.getInt("author_id"), resultSet.getString("first_name"), resultSet.getString("last_name"));
            return entity;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<Author> findAll() {
        List<Author> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author;");
            while (resultSet.next())
            list.add(new Author(resultSet.getInt("author_id"), resultSet.getString("first_name"), resultSet.getString("last_name")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
