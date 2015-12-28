package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.StudentGroupDAO;
import com.nixsolutions.entity.StudentGroup;
import com.nixsolutions.util.ConnectionManager;

public class StudentGroupDAOImpl implements StudentGroupDAO {

	private static Logger LOG = LogManager.getLogger(StudentGroupDAOImpl.class.getName());

	@Override
	public StudentGroup createStudentGroup(int groupId, String groupName) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO student_group VALUES( ?, ?)")) {
				ps.setInt(1, groupId);
				ps.setString(2, groupName);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return new StudentGroup(groupId, groupName);

	}

	@Override
	public void updateStudentGroup(StudentGroup group) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn
					.prepareStatement("UPDATE student_group SET group_name = ? WHERE group_id = ?")) {
				ps.setString(1, group.getGroupName());
				ps.setInt(2, group.getGroupId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}

	}

	@Override
	public void deleteStudentGroup(StudentGroup group) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE from student_group WHERE group_id = ?")) {
				ps.setInt(1, group.getGroupId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public StudentGroup findStudentGroupById(int groupId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student_group WHERE group_id = ?")) {
				ps.setInt(1, groupId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new StudentGroup(rs.getInt("group_id"), rs.getString("group_name"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<StudentGroup> findAllStudentGroups() {
		List<StudentGroup> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student_group")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					StudentGroup group = new StudentGroup();
					group.setGroupId(rs.getInt("group_id"));
					group.setGroupName(rs.getString("group_name"));
					result.add(group);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

}
