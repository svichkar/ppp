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
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO category (name) VALUES ('" + entity.getName() + "');");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void update(Category entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE category SET name='" + entity.getName() + "' WHERE category_id='" +
                    entity.getCategoryId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Category entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM category WHERE category_id='" + entity.getCategoryId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Category findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE category_id = '" + id + "';");
            resultSet.next();
            Category entity = new Category(resultSet.getInt("category_id"), resultSet.getString("name"));
            return entity;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category;");
            while (resultSet.next())
                list.add(new Category(resultSet.getInt("category_id"), resultSet.getString("name")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
