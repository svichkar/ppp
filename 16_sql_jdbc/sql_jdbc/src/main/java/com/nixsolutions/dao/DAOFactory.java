package com.nixsolutions.dao;

import com.nixsolutions.dao.impl.GradeDAOImpl;
import com.nixsolutions.dao.impl.JournalDAOImpl;
import com.nixsolutions.dao.impl.StatusDAOImpl;
import com.nixsolutions.dao.impl.StudentDAOImpl;
import com.nixsolutions.dao.impl.StudentGroupDAOImpl;
import com.nixsolutions.dao.impl.SubjectDAOImpl;
import com.nixsolutions.dao.impl.TermDAOImpl;

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
}
