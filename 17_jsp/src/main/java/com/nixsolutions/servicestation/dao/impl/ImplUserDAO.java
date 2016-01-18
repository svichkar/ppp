package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.UserDAO;
import com.nixsolutions.servicestation.entity.User;
import com.nixsolutions.servicestation.entity.extendedentity.UserClientBean;
import com.nixsolutions.servicestation.util.CustomConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by rybkinrolla on 06.01.2016.
 */
public class ImplUserDAO implements UserDAO{
    public static Logger LOGGER = LogManager.getLogger(ImplUserDAO.class.getName());

    @Override
    public void create(User entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO user " +
                     "(login, password, role_id) VALUES (?, ?, ?);")) {
            pStatement.setString(1, entity.getLogin());
            pStatement.setString(2, entity.getPassword());
            pStatement.setInt(3, entity.getRoleId());
            pStatement.execute();
            LOGGER.trace("Row in user was created");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(User entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE user " +
                     "SET login=?, password=?, role_id=? WHERE user_id=?;")) {
            pStatement.setString(1, entity.getLogin());
            pStatement.setString(2, entity.getPassword());
            pStatement.setInt(3, entity.getRoleId());
            pStatement.setInt(4, entity.getUserId());
            pStatement.execute();
            LOGGER.trace("Row in user with id = " + entity.getUserId() + " was updated");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(User entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM user " +
                     "WHERE user_id=?;")) {
            pStatement.setInt(1, entity.getUserId());
            pStatement.execute();
            LOGGER.trace("Row in user with id = " + entity.getUserId() + " was deleted");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public User findById(Integer id) {
        User user = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM user " +
                     "WHERE user_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            user = new User();
            if (rSet.next()) {
                user.setUserId(rSet.getInt("user_id"));
                user.setLogin(rSet.getString("login"));
                user.setPassword(rSet.getString("password"));
                user.setRoleId(rSet.getInt("role_id"));
            } else {
                user=null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (user != null) {
            LOGGER.trace("Row in user with id = " + user.getUserId() + " was found");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM user;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                User user = new User();
                user.setUserId(rSet.getInt("user_id"));
                user.setLogin(rSet.getString("login"));
                user.setPassword(rSet.getString("password"));
                user.setRoleId(rSet.getInt("role_id"));
                users.add(user);
                i++;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in user were found");
        return users;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM user " +
                     "WHERE login=?;")) {
            pStatement.setString(1, login);
            ResultSet rSet = pStatement.executeQuery();
            user = new User();
            if (rSet.next()) {
                user.setUserId(rSet.getInt("user_id"));
                user.setLogin(rSet.getString("login"));
                user.setPassword(rSet.getString("password"));
                user.setRoleId(rSet.getInt("role_id"));
            } else {
                user=null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (user != null) {
            LOGGER.trace("Row in user with login = " + user.getLogin() + " was found");
        }
        return user;
    }

    public List<UserClientBean> findClientsUsers() {
        List<UserClientBean> ucbList = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT u.user_id, u.login, u.password, " +
                     "c.client_id, c.first_name, c.last_name, r.role_name, r.role_id FROM user u " +
                     "INNER JOIN client c ON u.user_id = c.user_id "+
                     "INNER JOIN role r ON u.role_id = r.role_id;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                UserClientBean ucb = new UserClientBean();
                ucb.setClientId(rSet.getInt("client_id"));
                ucb.setClientFName(rSet.getString("first_name"));
                ucb.setClientLName(rSet.getString("last_name"));
                ucb.setUserId(rSet.getInt("user_id"));
                ucb.setLogin(rSet.getString("login"));
                ucb.setPassword(rSet.getString("password"));
                ucb.setRole(rSet.getString("role_name"));
                ucb.setRoleId(rSet.getString("role_id"));
                ucbList.add(ucb);
                i++;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (ucbList != null) {
            LOGGER.trace(i + " rows by findClientsUsers were found");
        }
        return ucbList;
    }
}
