package database.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {
	
	public Connection getConnection() throws SQLException;
	
	public JournalDao getJournalDao(Connection connection);
	
	public RateDao getRateDao(Connection connection);
	
	public RoleDao getRoleDao(Connection connection);
	
	public StatusDao getStatusDao(Connection connection);
	
	public StudentDao getStudentDao(Connection connection);
	
	public StudentGroupDao getStudentGroupDao(Connection connection);
	
	public SubjectDao getSubjectDao(Connection connection);
	
	public TermDao getTermDao(Connection connection);

	public UserDao getUserDao(Connection connection);
}
