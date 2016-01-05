package com.nixsolutions.studentgrade.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.studentgrade.dao.StatusDAO;
import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class StatusDAOImpl implements StatusDAO {

	private static Logger LOG = LogManager.getLogger(StatusDAOImpl.class.getName());

	@Override
	public Status createStatus(int statusId, String statusName) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO status VALUES( ?, ?)")) {
				ps.setInt(1, statusId);
				ps.setString(2, statusName);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return new Status(statusId, statusName);
	}

	@Override
	public void updateStatus(Status status) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn
					.prepareStatement("UPDATE status SET status_name = ? WHERE status_id = ?")) {
				ps.setString(1, status.getStatusName());
				ps.setInt(2, status.getStatusId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void deleteStatus(Status status) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE from status WHERE status_id = ?")) {
				ps.setInt(1, status.getStatusId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public Status findStatusById(int statusId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM status WHERE status_id = ?")) {
				ps.setInt(1, statusId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Status(rs.getInt("status_id"), rs.getString("status_name"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Status> findAllStatuses() {
		List<Status> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM status")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					Status status = new Status();
					status.setStatusId(rs.getInt("status_id"));
					status.setStatusName(rs.getString("status_name"));
					result.add(status);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

}
