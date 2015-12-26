package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.BookDAO;
import com.nixsolutions.library.entity.Book;
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
public class BookDaoImpl implements BookDAO {
    public static Logger LOGGER = LogManager.getLogger(BookDaoImpl.class.getName());

    @Override
    public Book create(Book entity) {
        Connection connection = null;
        Book newEntity = null;
        try {
            connection = CustomConnectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO book (name, cell_id, category_id) VALUES ('" + entity.getName() +
                    "' ,'" + entity.getCellId() + "' ,'" + entity.getCategoryId() + "');");
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            newEntity = new Book(keys.getInt(1), entity.getName(), entity.getCellId(), entity.getCategoryId());
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
    public void update(Book entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE book SET name='" + entity.getName() + "', cell_id='" +
                    entity.getCellId() + "', category_id='" + entity.getCategoryId() + "' WHERE book_id='" +
                    entity.getBookId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Book entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM book WHERE book_id='" + entity.getBookId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Book findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE book_id = '" + id + "';");
            if (resultSet.next()) {
                Book entity = new Book(resultSet.getInt("book_id"), resultSet.getString("name"), resultSet.getInt("cell_id"), resultSet.getInt("category_id"));
                return entity;
            } else {
                LOGGER.trace("id " + id + " not found in book table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book;");
            while (resultSet.next())
                list.add(new Book(resultSet.getInt("book_id"), resultSet.getString("name"), resultSet.getInt("cell_id"), resultSet.getInt("category_id")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
