package com.nixsolutions.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.nixsolutions.util.ConnectionManager;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.dao.RateDao;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.dao.TermDao;
import com.nixsolutions.dao.UserDao;

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
