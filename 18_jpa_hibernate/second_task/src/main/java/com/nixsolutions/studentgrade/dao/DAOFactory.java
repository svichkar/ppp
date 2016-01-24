package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.dao.impl.GradeDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.JournalDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.RoleDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.StatusDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.StudentDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.StudentGroupDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.SubjectDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.TermDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.UserDAOImpl;

public class DAOFactory {

	public static GradeDAO getGrade() {
		return new GradeDAOImpl();
	}

	public static JournalDAO getJournal() {
		return new JournalDAOImpl();
	}

	public static StatusDAO getStatus() {
		return new StatusDAOImpl();
	}

	public static StudentDAO getStudent() {
		return new StudentDAOImpl();
	}

	public static StudentGroupDAO getStudentGroup() {
		return new StudentGroupDAOImpl();
	}

	public static SubjectDAO getSubject() {
		return new SubjectDAOImpl();
	}

	public static TermDAO getTerm() {
		return new TermDAOImpl();
	}
	
	public static RoleDAO getRole() {
		return new RoleDAOImpl();
	}
	
	public static UserDAO getUser() {
		return new UserDAOImpl();
	}
}
