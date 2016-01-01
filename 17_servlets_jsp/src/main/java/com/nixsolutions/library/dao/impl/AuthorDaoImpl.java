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
        Connection connection = null;
        Author newEntity = null;
        try {
            connection = CustomConnectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO author (first_name, last_name) VALUES ('" + entity.getFirstName() +
                    "' ,'" + entity.getLastName() + "');");
            ResultSet keys = statement.getGeneratedKeys();
            connection.commit();
            keys.next();
            newEntity = this.findByID(keys.getInt(1));
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            LOGGER.trace("added line in author table, with id:" + newEntity.getAuthorId());
            return newEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
            return newEntity;
        }
    }

    @Override
    public void update(Author entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE author SET first_name='" + entity.getFirstName() + "', last_name='" +
                    entity.getLastName() + "' WHERE author_id='" + entity.getAuthorId() + "';");
            LOGGER.trace("updated line in author table, with id:" + entity.getAuthorId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Author entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM author WHERE author_id='" + entity.getAuthorId() + "';");
            LOGGER.trace("deleted line in author table, with id:" + entity.getAuthorId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Author findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author WHERE author_id = '" + id + "';");
            if (resultSet.next()) {
                Author entity = new Author(resultSet.getInt("author_id"), resultSet.getString("first_name"), resultSet.getString("last_name"));
                return entity;
            } else {
                LOGGER.trace("id " + id + " not found in author table");
                return null;
            }
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
