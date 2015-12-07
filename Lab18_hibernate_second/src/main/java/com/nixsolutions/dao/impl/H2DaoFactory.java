package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.*;

public class H2DaoFactory implements DaoFactory {

	public H2DaoFactory() throws ClassNotFoundException {
	}

	public JournalDao getJournalDao() {
		return new JournalDaoImpl();
	}

	public RateDao getRateDao() {
		return new RateDaoImpl();
	}

	public StatusDao getStatusDao() {
		return new StatusDaoImpl();
	}

	public StudentDao getStudentDao() {
		return new StudentDaoImpl();
	}

	public StudentGroupDao getStudentGroupDao() {
		return new StudentGroupDaoImpl();
	}

	public SubjectDao getSubjectDao() {
		return new SubjectDaoImpl();
	}

	public TermDao getTermDao() {
		return new TermDaoImpl();
	}

	public RoleDao getRoleDao() {
		return new RoleDaoImpl();
	}

	public UserDao getUserDao() {
		return new UserDaoImpl();
	}

}
