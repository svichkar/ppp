package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.RoleDAO;
import com.nixsolutions.servicestation.entity.Role;
import com.nixsolutions.servicestation.util.CustomConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
public class ImplRoleDAO implements RoleDAO {
    public static Logger LOGGER = LogManager.getLogger(ImplRoleDAO.class.getName());

    @Override
    public void create(Role entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO role (role_id, role_name) " +
                     "VALUES (?, ?);")) {
            pStatement.setInt(1, entity.getRoleId());
            pStatement.setString(2, entity.getRoleName());
            pStatement.execute();
            LOGGER.trace("Row in role was created");
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Role entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE role " +
                     "SET role_name=? WHERE role_id=?;")) {
            pStatement.setString(1, entity.getRoleName());
            pStatement.setInt(2, entity.getRoleId());
            pStatement.execute();
            LOGGER.trace("Row in role with id = " + entity.getRoleId() + " was updated");
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Role entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM role " +
                     "WHERE role_id=?;")) {
            pStatement.setInt(1, entity.getRoleId());
            pStatement.execute();
            LOGGER.trace("Row in role with id = " + entity.getRoleId() + " was deleted");
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Role findById(Integer id) {
        Role role = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM role " +
                     "WHERE role_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            role = new Role();
            if (rSet.next()) {
                role.setRoleId(rSet.getInt("role_id"));
                role.setRoleName(rSet.getString("role_name"));
            } else {
                role=null;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        if (role != null) {
            LOGGER.trace("Row in employee_category with id = " + role.getRoleId() + " was found");
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM role;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                Role role = new Role();
                role.setRoleId(rSet.getInt("role_id"));
                role.setRoleName(rSet.getString("role_name"));
                roles.add(role);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in role were found");
        return roles;
    }
}
