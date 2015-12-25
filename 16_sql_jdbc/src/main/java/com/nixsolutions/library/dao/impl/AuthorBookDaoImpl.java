package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.AuthorBookDAO;
import com.nixsolutions.library.entity.AuthorBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozlovskij on 12/23/2015.
 */
public class AuthorBookDaoImpl implements AuthorBookDAO {
    public static Logger LOGGER = LogManager.getLogger(AuthorBookDaoImpl.class.getName());

    @Override
    public AuthorBook create(AuthorBook entity) {
        Connection connection = null;
        AuthorBook newEntity = null;
        try {
            connection = CustomConnectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO author_book (author_id, book_id) VALUES ('" + entity.getAuthorId() +
                    "' ,'" + entity.getBookId() + "');");
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            newEntity = new AuthorBook(keys.getInt(1), entity.getAuthorId(), entity.getBookId());
            connection.commit();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
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
    public void update(AuthorBook entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE author_book SET author_id='" + entity.getAuthorId() + "', book_id='" +
                    entity.getBookId() + "' WHERE id='" + entity.getId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(AuthorBook entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM author_book WHERE id='" + entity.getId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public AuthorBook findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author_book WHERE id = '" + id + "';");
            if (resultSet.next()) {
                AuthorBook entity = new AuthorBook(resultSet.getInt("id"), resultSet.getInt("author_id"), resultSet.getInt("book_id"));
                return entity;
            } else {
                LOGGER.trace("id " + id + " not found in author_book table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<AuthorBook> findAll() {
        List<AuthorBook> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author_book;");
            while (resultSet.next())
                list.add(new AuthorBook(resultSet.getInt("id"), resultSet.getInt("author_id"), resultSet.getInt("book_id")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
