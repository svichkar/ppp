package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StatusDao;
import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class StatusDaoImpl implements StatusDao {

    private static final Logger LOG = LogManager.getLogger(StatusDaoImpl.class);

    @Override
    public Status create(Status status) {

        String sql = "INSERT INTO status(status_id, status_name) VALUES ( ? , ? )";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, status.getStatusId());
            statement.setString(2, status.getStatusName());
            statement.executeUpdate();
            return status;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public boolean update(Status status) {

        String sql = "UPDATE status SET status_name = ? WHERE status_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status.getStatusName());
            statement.setInt(2, status.getStatusId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(Status status) {

        String sql = "DELETE FROM status WHERE status_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, status.getStatusId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public List<Status> findAll() {

        String sql = "SELECT * FROM status";
        List<Status> list = new ArrayList<>();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Status status = new Status();
                status.setStatusId(rs.getInt("status_id"));
                status.setStatusName(rs.getString("status_name"));
                list.add(status);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Status findById(int id) {

        String sql = String.format("SELECT status_id, status_name FROM status WHERE status_id = %d", id);
        Status result = new Status();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setStatusId(rs.getInt("status_id"));
                result.setStatusName(rs.getString("status_name"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }
}
