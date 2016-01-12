package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.util.H2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/23/2015.
 */
public class UserDaoImpl implements UserDao {

    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);

    public boolean create(User user) {

        String sql = "INSERT INTO user (first_name, last_name, email, login, password, role_id) " +
                "VALUES ( ?, ?, ?, ?, ?, ?)";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getUserPassword());
            statement.setLong(6, user.getRoleId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    public boolean update(User user) {

        String sql = "UPDATE user SET first_name = ?, last_name = ?, email = ?, login = ?, password = ?, " +
                "role_id = ? WHERE user_id = ?";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getUserPassword());
            statement.setLong(6, user.getRoleId());
            statement.setLong(7, user.getUserId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    public boolean delete(User user) {

        String sql = "DELETE FROM user WHERE user_id = ?";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, user.getUserId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    public List<User> findAll() {

        String sql = "SELECT * FROM user";
        List<User> list = new ArrayList<>();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getLong("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setLogin(rs.getString("login"));
                user.setUserPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRoleId(rs.getLong("role_id"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    public boolean validateUser(String user) {

        String sql = String.format("SELECT user_id FROM user WHERE login = '%s'", user);

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            return rs.first();
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    public User getUserByLoginAndPassword(String user, String pass) {

        String sql = String.format("SELECT * FROM user " +
                "WHERE login = '%s' AND password = '%s'", user, pass);

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Long id = rs.getLong("user_id");
                String name = rs.getString("first_name");
                String last = rs.getString("last_name");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Long role = rs.getLong("role_id");
                return new User(id, name, last, login, password, email, role);
            }
            return null;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

}
