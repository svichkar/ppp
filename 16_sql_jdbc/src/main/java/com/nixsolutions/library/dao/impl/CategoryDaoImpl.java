package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.CategoryDAO;
import com.nixsolutions.library.entity.Category;
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
public class CategoryDaoImpl implements CategoryDAO {
    public static Logger LOGGER = LogManager.getLogger(CategoryDaoImpl.class.getName());

    @Override
    public Category create(Category entity) {

        Connection connection = null;
        Statement statement = null;
        Category newEntity = null;
        try {
            connection = CustomConnectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO category (name) VALUES ('" + entity.getName() + "');");
            ResultSet keys = statement.getGeneratedKeys();
            connection.commit();
            keys.next();
            newEntity = this.findByID(keys.getInt(1));
            LOGGER.trace("added line in category table, with id:" + newEntity.getCategoryId());
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
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
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
    public void update(Category entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE category SET name='" + entity.getName() + "' WHERE category_id='" +
                    entity.getCategoryId() + "';");
            LOGGER.trace("updated line in category table, with id:" + entity.getCategoryId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Category entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM category WHERE category_id='" + entity.getCategoryId() + "';");
            LOGGER.trace("deleted line in category table, with id:" + entity.getCategoryId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Category findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE category_id = '" + id + "';");
            if (resultSet.next()) {
                Category entity = new Category(resultSet.getInt("category_id"), resultSet.getString("name"));
                return entity;
            } else {
                LOGGER.trace("id " + id + " not found in category table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category;");
            while (resultSet.next())
                list.add(new Category(resultSet.getInt("category_id"), resultSet.getString("name")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public Category findByName(String name) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE name = '" + name + "';");
            if (resultSet.next()) {
                Category entity = new Category(resultSet.getInt("category_id"), resultSet.getString("name"));
                return entity;
            } else {
                LOGGER.trace("name " + name + " not found in category table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
