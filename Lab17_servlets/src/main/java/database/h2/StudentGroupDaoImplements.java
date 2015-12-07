package database.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.dao.StudentGroupDao;
import database.entities.StudentGroup;

public class StudentGroupDaoImplements implements StudentGroupDao {
	private final static Logger LOG = LogManager.getLogger(StudentGroupDaoImplements.class.getName());
	private Connection conn;

	public StudentGroupDaoImplements(Connection connection) {
		conn = connection;
	}
	public void create(String studentGroupName) {
		PreparedStatement stm = null;
		try {
			String sql = "INSERT INTO student_group (group_name) VALUES (?);";
			stm = conn.prepareStatement(sql);
			stm.setString(1, studentGroupName);
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
	}
	
	public void update(StudentGroup studentGroup) {
		PreparedStatement stm = null;
		String sql = "UPDATE student_group SET group_name=? WHERE student_group_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(2, studentGroup.getId());
			stm.setString(1, studentGroup.getGroupName());
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
	}

	public void delete(StudentGroup studentGroup) {
		PreparedStatement stm = null;
		String sql = "DELETE FROM student_group WHERE student_group_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, studentGroup.getId());
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
	}

	public StudentGroup getByStudentGroupId(int studentGroupId) {
		StudentGroup studentGroup = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM student_group WHERE student_group_id = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, studentGroupId);
			ResultSet rs = stm.executeQuery();
			rs.next();
			studentGroup = new StudentGroup(rs.getInt("student_group_id"), rs.getString("group_name"));
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
		return studentGroup;
	}

	public StudentGroup getByStudentGroupName(String studentGroupName) {
		StudentGroup studentGroup = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM student_group WHERE group_name = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, studentGroupName);
			ResultSet rs = stm.executeQuery();
			rs.next();
			studentGroup = new StudentGroup(rs.getInt("student_group_id"), rs.getString("group_name"));
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
		return studentGroup;
	}

	public List<StudentGroup> getAll() {
		List<StudentGroup> toReturn = new ArrayList<StudentGroup>();
		Statement stm = null;
		String sql = "SELECT * FROM student_group;";
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				toReturn.add(new StudentGroup(rs.getInt("student_group_id"), rs.getString("group_name")));
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
		}
		return toReturn;
	}

}
