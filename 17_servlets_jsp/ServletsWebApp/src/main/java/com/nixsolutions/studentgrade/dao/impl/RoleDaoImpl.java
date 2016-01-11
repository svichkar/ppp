package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/23/2015.
 */
public class RoleDaoImpl implements RoleDao {

    private static final Logger LOG = LogManager.getLogger(RoleDaoImpl.class);

    public Role create(Role role) {

        String sql = "INSERT INTO role (role_id, role_name) VALUES ( ?, ?)";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, role.getRoleId());
            statement.setString(2, role.getRoleName());
            statement.executeUpdate();
            return role;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    public boolean update(Role role) {

        String sql = "UPDATE role SET role_name = ? WHERE role_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, role.getRoleName());
            statement.setInt(2, role.getRoleId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    public boolean delete(Role role) {

        String sql = "DELETE FROM role WHERE role_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, role.getRoleId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    public List<Role> findAll() {

        String sql = "SELECT * FROM role";
        List<Role> list = new ArrayList<>();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                list.add(role);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    public Role findById(int id) {

        String sql = String.format("SELECT role_id, role_name FROM role WHERE role_id = %d", id);
        Role result = new Role();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int roleId = rs.getInt("role_id");
                String roleName = rs.getString("role_name");
                return new Role(roleId, roleName);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return null;
    }

    public Role findByName(String role) {

        String sql = String.format("SELECT role_id, role_name FROM role WHERE role_name = '%s'", role);

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int roleId = rs.getInt("role_id");
                String roleName = rs.getString("role_name");
                return new Role(roleId, roleName);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return null;
    }
}
