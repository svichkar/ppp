package com.nixsolutions.studentgrade.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class StudentDAOImpl implements StudentDAO {
	private static Logger LOG = LogManager.getLogger(StudentDAOImpl.class.getName());

	@Override
	public void createStudent(Student student) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO student VALUES( ?, ?, ?, ?, ?, ?, ?)")) {
				ps.setLong(1, student.getStudentId());
				ps.setString(2, student.getFirstName());
				ps.setString(3, student.getLastName());
				ps.setLong(4, student.getGroupId());
				ps.setDate(5, student.getAdmissionDate());
				ps.setInt(6, student.getStatusId());
				ps.setLong(7, student.getTermId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void updateStudent(Student student) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(
					"UPDATE student SET first_name = ?, last_name=?, group_id=?, admission_date=?, status_id=?, term_id=? WHERE student_id = ?")) {
				ps.setString(1, student.getFirstName());
				ps.setString(2, student.getLastName());
				ps.setLong(3, student.getGroupId());
				ps.setDate(4, student.getAdmissionDate());
				ps.setInt(5, student.getStatusId());
				ps.setLong(6, student.getTermId());
				ps.setLong(7, student.getStudentId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}

	}

	@Override
	public void deleteStudent(Student student) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE from student WHERE student_id = ?")) {
				ps.setLong(1, student.getStudentId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}

	}

	@Override
	public Student findStudentById(Long studentId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE student_id = ?")) {
				ps.setLong(1, studentId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Student(rs.getLong("student_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getLong("group_id"), rs.getDate("admission_date"), rs.getInt("status_id"),
						rs.getLong("term_id"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Student> findAllStudents() {
		List<Student> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					Student student = new Student();
					student.setStudentId(rs.getLong("student_id"));
					student.setFirstName(rs.getString("first_name"));
					student.setLastName(rs.getString("last_name"));
					student.setGroupId(rs.getLong("group_id"));
					student.setAdmissionDate(rs.getDate("admission_date"));
					student.setStatusId(rs.getInt("status_id"));
					student.setTermId(rs.getLong("term_id"));
					result.add(student);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Student> findStudentsByLastNameAndGroupId(String lastName, Long groupId) {
		List<Student> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE last_name = ? and group_id = ?")) {
				ps.setString(1, lastName);
				ps.setLong(2, groupId);
				rs = ps.executeQuery();
				while (rs.next()) {
					Student student = new Student();
					student.setStudentId(rs.getLong("student_id"));
					student.setFirstName(rs.getString("first_name"));
					student.setLastName(rs.getString("last_name"));
					student.setGroupId(rs.getLong("group_id"));
					student.setAdmissionDate(rs.getDate("admission_date"));
					student.setStatusId(rs.getInt("status_id"));
					student.setTermId(rs.getLong("term_id"));
					result.add(student);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}


	@Override
	public List<Student> findStudentsByLastName(String lastName) {
		List<Student> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE last_name = ?")) {
				ps.setString(1, lastName);
				rs = ps.executeQuery();
				while (rs.next()) {
					Student student = new Student();
					student.setStudentId(rs.getLong("student_id"));
					student.setFirstName(rs.getString("first_name"));
					student.setLastName(rs.getString("last_name"));
					student.setGroupId(rs.getLong("group_id"));
					student.setAdmissionDate(rs.getDate("admission_date"));
					student.setStatusId(rs.getInt("status_id"));
					student.setTermId(rs.getLong("term_id"));
					result.add(student);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Student> findStudentsByGroupId(Long groupId) {
		List<Student> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE group_id = ?")) {
				ps.setLong(1, groupId);
				rs = ps.executeQuery();
				while (rs.next()) {
					Student student = new Student();
					student.setStudentId(rs.getLong("student_id"));
					student.setFirstName(rs.getString("first_name"));
					student.setLastName(rs.getString("last_name"));
					student.setGroupId(rs.getLong("group_id"));
					student.setAdmissionDate(rs.getDate("admission_date"));
					student.setStatusId(rs.getInt("status_id"));
					student.setTermId(rs.getLong("term_id"));
					result.add(student);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

}
