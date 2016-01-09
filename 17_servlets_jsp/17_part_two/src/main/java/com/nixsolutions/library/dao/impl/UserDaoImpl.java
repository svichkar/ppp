package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.UserDAO;

import com.nixsolutions.library.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozlovskij on 12/28/2015.
 */
public class UserDaoImpl implements UserDAO {
    public static Logger LOGGER = LogManager.getLogger(UserDaoImpl.class.getName());

    @Override
    public User create(User entity) {
        Connection connection = null;
        Statement statement = null;
        User newEntity = null;
        try {
            connection = CustomConnectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user (login, password, role_id) VALUES ('" +
                    entity.getLogin() + "', '" + entity.getPassword() + "', '" + entity.getRoleId() + "');");
            ResultSet keys = statement.getGeneratedKeys();
            connection.commit();
            keys.next();
            newEntity = this.findByID(keys.getInt(1));
            LOGGER.trace("added line in user table, with id:" + newEntity.getRoleId());
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
    public void update(User entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE user SET login='" + entity.getLogin() + "', password='" +
                    entity.getPassword() + "', role_id='" + entity.getRoleId() + "' WHERE user_id='" +
                    entity.getUserId() + "';");
            LOGGER.trace("updated line in user table, with id:" + entity.getRoleId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(User entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM user WHERE user_id='" + entity.getUserId() + "';");
            LOGGER.trace("deleted line in user table, with id:" + entity.getUserId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public User findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE user_id = '" + id + "';");
            if (resultSet.next()) {
                User entity = new User(resultSet.getInt("user_id"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getInt("role_id"));
                return entity;
            } else {
                LOGGER.trace("id " + id + " not found in user table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user;");
            while (resultSet.next())
                list.add(new User (resultSet.getInt("user_id"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getInt("role_id")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
    public User findByLogin(String login) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE login = '" + login.toLowerCase() + "';");
            if (resultSet.next()) {
                User entity = new User(resultSet.getInt("user_id"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getInt("role_id"));
                return entity;
            } else {
                LOGGER.trace("login " + login + " not found in user table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
