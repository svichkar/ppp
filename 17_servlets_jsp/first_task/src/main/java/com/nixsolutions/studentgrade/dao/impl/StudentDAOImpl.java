package com.nixsolutions.studentgrade.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class StudentDAOImpl implements StudentDAO {
	private static Logger LOG = LogManager.getLogger(StudentDAOImpl.class.getName());

	@Override
	public Student createStudent(int studentId, String firstName, String lastName, int groupId, Date admissionDate,
			int statusId, int termId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO student VALUES( ?, ?, ?, ?, ?, ?, ?)")) {
				ps.setInt(1, studentId);
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setInt(4, groupId);
				ps.setDate(5, admissionDate);
				ps.setInt(6, statusId);
				ps.setInt(7, termId);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return new Student(studentId, firstName, lastName, groupId, admissionDate, statusId, termId);
	}

	@Override
	public void updateStudent(Student student) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(
					"UPDATE student SET first_name = ?, last_name=?, group_id=?, admission_date=?, status_id=?, term_id=? WHERE student_id = ?")) {
				ps.setString(1, student.getFirstName());
				ps.setString(2, student.getLastName());
				ps.setInt(3, student.getGroupId());
				ps.setDate(4, student.getAdmissionDate());
				ps.setInt(5, student.getStatusId());
				ps.setInt(6, student.getTermId());
				ps.setInt(7, student.getStudentId());
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
				ps.setInt(1, student.getStudentId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}

	}

	@Override
	public Student findStudentById(int studentId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE student_id = ?")) {
				ps.setInt(1, studentId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Student(rs.getInt("student_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getInt("group_id"), rs.getDate("admission_date"), rs.getInt("status_id"),
						rs.getInt("term_id"));
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
					student.setStudentId(rs.getInt("student_id"));
					student.setFirstName(rs.getString("first_name"));
					student.setLastName(rs.getString("last_name"));
					student.setGroupId(rs.getInt("group_id"));
					student.setAdmissionDate(rs.getDate("admission_date"));
					student.setStatusId(rs.getInt("status_id"));
					student.setTermId(rs.getInt("term_id"));
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
