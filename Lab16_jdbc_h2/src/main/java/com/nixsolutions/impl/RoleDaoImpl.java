package com.nixsolutions.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.util.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;

public class RoleDaoImpl implements RoleDao {

    private final static Logger LOG = LogManager.getLogger(RoleDaoImpl.class.getName());

    public RoleDaoImpl() {
    }

    public void create(String roleName) {
        PreparedStatement stm = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            String sql = "INSERT INTO role (role_name) VALUES (?);";
            stm = conn.prepareStatement(sql);
            stm.setString(1, roleName);
            stm.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            ConnectionManager.releaseConnection(conn);
        }
    }

    public void update(Role role) {
        PreparedStatement stm = null;
        Connection conn = null;
        String sql = "UPDATE role SET role_name=? WHERE role_id=?;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(2, role.getId());
            stm.setString(1, role.getRoleName());
            stm.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            ConnectionManager.releaseConnection(conn);
        }
    }

    public void delete(Role role) {
        PreparedStatement stm = null;
        Connection conn = null;
        String sql = "DELETE FROM role WHERE role_id=?;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, role.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        ConnectionManager.releaseConnection(conn);
    }

    public Role getByRoleId(int roleId) {
        Role role = null;
        PreparedStatement stm = null;
        Connection conn = null;
        String sql = "SELECT * FROM role WHERE role_id = ?;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, roleId);
            ResultSet rs = stm.executeQuery();
            rs.next();
            role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            ConnectionManager.releaseConnection(conn);
        }
        return role;
    }

    public Role getByRoleName(String roleName) {
        Role role = null;
        PreparedStatement stm = null;
        Connection conn = null;
        String sql = "SELECT * FROM role WHERE role_name = ?;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, roleName);
            ResultSet rs = stm.executeQuery();
            rs.next();
            role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            ConnectionManager.releaseConnection(conn);
        }
        return role;
    }

    public List<Role> getAll() {
        List<Role> toReturn = new ArrayList<Role>();
        Statement stm = null;
        Connection conn = null;
        String sql = "SELECT * FROM role;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                toReturn.add(new Role(rs.getInt("role_id"), rs.getString("role_name")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            ConnectionManager.releaseConnection(conn);
        }
        return toReturn;
    }
}