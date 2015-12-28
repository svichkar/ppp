package com.nixsolutions.dao;

import com.nixsolutions.dao.impl.GradeDAOImpl;
import com.nixsolutions.dao.impl.JournalDAOImpl;
import com.nixsolutions.dao.impl.StatusDAOImpl;
import com.nixsolutions.dao.impl.StudentDAOImpl;
import com.nixsolutions.dao.impl.StudentGroupDAOImpl;
import com.nixsolutions.dao.impl.SubjectDAOImpl;
import com.nixsolutions.dao.impl.TermDAOImpl;

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
}
