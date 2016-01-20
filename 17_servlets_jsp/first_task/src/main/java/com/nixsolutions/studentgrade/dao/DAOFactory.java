package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.dao.impl.*;

public class DAOFactory {

	public static GradeDAOImpl getGrade() {
		return new GradeDAOImpl();
	}

	public static JournalDAOImpl getJournal() {
		return new JournalDAOImpl();
	}

	public static StatusDAOImpl getStatus() {
		return new StatusDAOImpl();
	}

	public static StudentDAOImpl getStudent() {
		return new StudentDAOImpl();
	}

	public static StudentGroupDAOImpl getStudentGroup() {
		return new StudentGroupDAOImpl();
	}

	public static SubjectDAOImpl getSubject() {
		return new SubjectDAOImpl();
	}

	public static TermDAOImpl getTerm() {
		return new TermDAOImpl();
	}
	
	public static RoleDAOImpl getRole() {
		return new RoleDAOImpl();
	}
	
	public static UserDAOImpl getUser() {
		return new UserDAOImpl();
	}
}
