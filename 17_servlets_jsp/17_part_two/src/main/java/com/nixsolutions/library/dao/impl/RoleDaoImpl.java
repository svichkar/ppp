package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.RoleDAO;
import com.nixsolutions.library.entity.Role;
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
public class RoleDaoImpl implements RoleDAO {
    public static Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class.getName());

    @Override
    public Role create(Role entity) {
        Connection connection = null;
        Statement statement = null;
        Role newEntity = null;
        try {
            connection = CustomConnectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO role (name) VALUES ('" + entity.getName() + "');");
            ResultSet keys = statement.getGeneratedKeys();
            connection.commit();
            keys.next();
            newEntity = this.findByID(keys.getInt(1));
            LOGGER.trace("added line in role table, with id:" + newEntity.getRoleId());
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
    public void update(Role entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE role SET name='" + entity.getName() + "' WHERE role_id='" +
                    entity.getRoleId() + "';");
            LOGGER.trace("updated line in role table, with id:" + entity.getRoleId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Role entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM role WHERE role_id='" + entity.getRoleId() + "';");
            LOGGER.trace("deleted line in cell table, with id:" + entity.getRoleId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Role findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM role WHERE role_id = '" + id + "';");
            if (resultSet.next()) {
                Role entity = new Role(resultSet.getInt("role_id"), resultSet.getString("name"));
                return entity;
            } else {
                LOGGER.trace("id " + id + " not found in role table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM role;");
            while (resultSet.next())
                list.add(new Role(resultSet.getInt("role_id"), resultSet.getString("name")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public Role findByName(String name) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM role WHERE name = '" + name + "';");
            if (resultSet.next()) {
                Role entity = new Role(resultSet.getInt("role_id"), resultSet.getString("name"));
                return entity;
            } else {
                LOGGER.trace("name " + name + " not found in user table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
