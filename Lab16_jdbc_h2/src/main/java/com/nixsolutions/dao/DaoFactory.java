package com.nixsolutions.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

	public JournalDao getJournalDao();
	
	public RateDao getRateDao();
	
	public RoleDao getRoleDao();
	
	public StatusDao getStatusDao();
	
	public StudentDao getStudentDao();
	
	public StudentGroupDao getStudentGroupDao();
	
	public SubjectDao getSubjectDao();
	
	public TermDao getTermDao();

	public UserDao getUserDao();
}
