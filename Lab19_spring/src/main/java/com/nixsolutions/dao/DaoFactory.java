package com.nixsolutions.dao;

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
