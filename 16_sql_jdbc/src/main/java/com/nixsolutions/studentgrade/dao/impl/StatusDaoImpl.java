package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StatusDao;
import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class StatusDaoImpl implements StatusDao {

    @Override
    public boolean create(Status status) {
        String sql = "INSERT INTO status(status_id, status_name) VALUES ( ? , ? )";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, status.getStatusId());
            statement.setString(2, status.getStatusName());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int update(Status status, Status newStatus) {

        String sql = "UPDATE status SET status_id = ?, status_name = ? WHERE status_id = ? and status_name = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newStatus.getStatusId());
            statement.setString(2, newStatus.getStatusName());
            statement.setInt(3, status.getStatusId());
            statement.setString(4, status.getStatusName());
            return statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Status status) {

        String sql = "DELETE FROM status WHERE status_id = ? and status_name = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, status.getStatusId());
            statement.setString(2, status.getStatusName());
            return statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Status> findAll() {

        String sql = "SELECT * FROM status;";
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
