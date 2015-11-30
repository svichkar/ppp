package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.dao.StatusDao;
import database.entity.Status;
import database.util.ConnectionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatusDaoImpl implements StatusDao {
    private final static Logger LOG = LogManager.getLogger(StatusDaoImpl.class.getName());

    public StatusDaoImpl() {
    }

    public void create(String statusName) {
        PreparedStatement stm = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            String sql = "INSERT INTO status (status_name) VALUES (?);";
            stm = conn.prepareStatement(sql);
            stm.setString(1, statusName);
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

    public void update(Status status) {
        PreparedStatement stm = null;
        Connection conn = null;
        String sql = "UPDATE status SET status_name=? WHERE status_id=?;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(2, status.getId());
            stm.setString(1, status.getStatusName());
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

    public void delete(Status status) {
        PreparedStatement stm = null;
        Connection conn = null;
        String sql = "DELETE FROM status WHERE status_id=?;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, status.getId());
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

    public Status getByStatusId(int statusId) {
        Status status = null;
        Connection conn = null;
        PreparedStatement stm = null;
        String sql = "SELECT * FROM status WHERE status_id = ?;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, statusId);
            ResultSet rs = stm.executeQuery();
            rs.next();
            status = new Status(rs.getInt("status_id"), rs.getString("status_name"));
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
        return status;
    }

    public Status getByStatusName(String statusName) {
        Status status = null;
        Connection conn = null;
        PreparedStatement stm = null;
        String sql = "SELECT * FROM status WHERE status_name = ?;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, statusName);
            ResultSet rs = stm.executeQuery();
            rs.next();
            status = new Status(rs.getInt("status_id"), rs.getString("status_name"));
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
        return status;
    }

    public List<Status> getAll() {
        List<Status> toReturn = new ArrayList<Status>();
        Statement stm = null;
        Connection conn = null;
        String sql = "SELECT * FROM status;";
        try {
            conn = ConnectionManager.getConnection();
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                toReturn.add(new Status(rs.getInt("status_id"), rs.getString("status_name")));
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