package h2;

import java.sql.Connection;
import java.sql.SQLException;

import com.nixsolutions.CustomConnectionManager;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.dao.RateDao;
import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.dao.TermDao;

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

}
