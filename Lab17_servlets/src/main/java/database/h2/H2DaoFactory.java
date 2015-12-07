package database.h2;

import java.sql.Connection;
import java.sql.SQLException;

import database.CustomConnectionManager;
import database.dao.DaoFactory;
import database.dao.JournalDao;
import database.dao.RateDao;
import database.dao.RoleDao;
import database.dao.StatusDao;
import database.dao.StudentDao;
import database.dao.StudentGroupDao;
import database.dao.SubjectDao;
import database.dao.TermDao;
import database.dao.UserDao;

public class H2DaoFactory implements DaoFactory {

	public H2DaoFactory() throws ClassNotFoundException {
		Class.forName("org.h2.Driver");
	}

	public Connection getConnection() throws SQLException {
		return CustomConnectionManager.getConnection();
	}

	public JournalDao getJournalDao(Connection connection) {
		return new JournalDaoImplements(connection);
	}

	public RateDao getRateDao(Connection connection) {
		return new RateDaoImplements(connection);
	}

	public StatusDao getStatusDao(Connection connection) {
		return new StatusDaoImplements(connection);
	}

	public StudentDao getStudentDao(Connection connection) {
		return new StudentDaoImplements(connection);
	}

	public StudentGroupDao getStudentGroupDao(Connection connection) {
		return new StudentGroupDaoImplements(connection);
	}

	public SubjectDao getSubjectDao(Connection connection) {
		return new SubjectDaoImplements(connection);
	}

	public TermDao getTermDao(Connection connection) {
		return new TermDaoImplements(connection);
	}

	public RoleDao getRoleDao(Connection connection) {
		return new RoleDaoImplements(connection);
	}

	public UserDao getUserDao(Connection connection) {
		return new UserDaoImplements(connection);
	}

}
