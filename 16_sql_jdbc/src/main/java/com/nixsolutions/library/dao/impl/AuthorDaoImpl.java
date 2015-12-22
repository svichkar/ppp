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
    public boolean create(Author entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("INSERT INTO author (first_name, last_name) VALUES ('" + entity.getFirstName() + "' ,'" + entity.getLastName()+"');") > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }

    @Override
    public boolean update(Author entity) {
        if (entity.getAuthorId() != null) {
            try (Connection connection = CustomConnectionManager.getConnection()) {
                Statement statement = connection.createStatement();
                return statement.executeUpdate("UPDATE author SET first_name='" + entity.getFirstName() + "', last_name='" + entity.getLastName() + "' WHERE author_id='" + entity.getAuthorId() + "';") > 0;
            } catch (SQLException e) {
                LOGGER.error(e);
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Author entity) {
        if (this.getId(entity) != null) {
            try (Connection connection = CustomConnectionManager.getConnection()) {
                Statement statement = connection.createStatement();
                return statement.executeUpdate("DELETE FROM author WHERE author_id='" + entity.getAuthorId() + "';") > 0;
            } catch (SQLException e) {
                LOGGER.error(e);
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Integer getId(Author entity) {
        if (entity.getAuthorId() == null) {
            try (Connection connection = CustomConnectionManager.getConnection()) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT author_id FROM author WHERE first_name = '" + entity.getFirstName() + "' AND last_name ='" + entity.getLastName() + "';");
                resultSet.next();
                entity.setAuthorId(resultSet.getInt(1));
                return resultSet.getInt(1);
            } catch (SQLException e) {
                LOGGER.error(e);
                LOGGER.trace("Entity " + entity.getClass().getName() + "|" + entity.getFirstName() + "|" + entity.getLastName() + " not found in DB");
                return null;
            }
        } else {
            return entity.getAuthorId();
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
            LOGGER.trace("id " + id + " not found in DB");
            return null;
        }
    }

    @Override
    public List<Author> findAll() {
        List<Author> authorList = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author;");
            while (resultSet.next())
            authorList.add(new Author(resultSet.getInt("author_id"), resultSet.getString("first_name"), resultSet.getString("last_name")));
            return authorList;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
