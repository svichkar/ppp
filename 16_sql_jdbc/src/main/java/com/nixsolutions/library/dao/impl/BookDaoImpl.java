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
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO book (name, cell_id, category_id) VALUES ('" + entity.getName() +
                    "' ,'" + entity.getCellId() + "' ,'" + entity.getCategoryId() + "');");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
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
            resultSet.next();
            Book entity = new Book(resultSet.getInt("book_id"), resultSet.getString("name"), resultSet.getInt("cell_id"), resultSet.getInt("category_name"));
            return entity;
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
                list.add(new Book(resultSet.getInt("book_id"), resultSet.getString("name"), resultSet.getInt("cell_id"), resultSet.getInt("category_name")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
