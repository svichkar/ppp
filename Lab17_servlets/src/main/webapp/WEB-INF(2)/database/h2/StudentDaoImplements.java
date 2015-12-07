package database.h2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.dao.StudentDao;
import database.entities.Student;

public class StudentDaoImplements implements StudentDao {
	private final static Logger LOG = LogManager.getLogger(StudentDaoImplements.class.getName());
	private Connection conn;

	public StudentDaoImplements(Connection connection) {
		conn = connection;
	}
	
	public void create(String firstName, String lastName, Date dateBirthday, Date dateEntry, int studentGroupId,
			int termId, int statusId) {
		PreparedStatement stm = null;
		try {
			String sql = "INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
			stm = conn.prepareStatement(sql);
			stm.setString(1, firstName);
			stm.setString(2, lastName);
			stm.setDate(3, dateBirthday);
			stm.setDate(4, dateEntry);
			stm.setInt(5, studentGroupId);
			stm.setInt(6, termId);
			stm.setInt(7, statusId);
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

	public void update(Student student) {
		PreparedStatement stm = null;
		String sql = "UPDATE student SET first_name=?, last_name=?, date_birthday=?, date_entry=?, "
				+ "student_group_id=?, term_id=?, status_id=?  WHERE student_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(8, student.getId());
			stm.setString(1, student.getFirstName());
			stm.setString(2, student.getLastName());
			stm.setDate(3, student.getDateBirthday());
			stm.setDate(4, student.getDateEntry());
			stm.setInt(5, student.getStudentGroupId());
			stm.setInt(6, student.getTermId());
			stm.setInt(7, student.getStatusId());
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

	public void delete(Student student) {
		PreparedStatement stm = null;
		String sql = "DELETE FROM student WHERE student_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, student.getId());
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

	public Student getByStudentId(int studentId) {
		Student student = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM student WHERE student_id = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, studentId);
			ResultSet rs = stm.executeQuery();
			rs.next();
			student = new Student(rs.getInt("student_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("date_birthday"), 
					rs.getDate("date_entry"), rs.getInt("student_group_id"), rs.getInt("term_id"), rs.getInt("status_id"));
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
		return student;
	}

	public List<Student> getByStudentsByName(String firstName, String lastName) {
		List<Student> student = new ArrayList<Student>();
		PreparedStatement stm = null;
		String sql = "SELECT * FROM student WHERE first_name = ? AND last_name = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, firstName);
			stm.setString(2, lastName);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
			student.add(new Student(rs.getInt("student_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("date_birthday"), 
					rs.getDate("date_entry"), rs.getInt("student_group_id"), rs.getInt("term_id"), rs.getInt("status_id")));
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
		return student;
	}

	public List<Student> getAll() {
		List<Student> toReturn = new ArrayList<Student>();
		Statement stm = null;
		String sql = "SELECT * FROM student;";
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				toReturn.add(new Student(rs.getInt("student_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("date_birthday"), 
						rs.getDate("date_entry"), rs.getInt("student_group_id"), rs.getInt("term_id"), rs.getInt("status_id")));
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
