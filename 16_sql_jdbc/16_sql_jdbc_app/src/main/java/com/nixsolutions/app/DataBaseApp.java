package com.nixsolutions.app;

import java.sql.Date;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.impl.JournalDaoImpl;
import com.nixsolutions.dao.impl.StatusDaoImpl;
import com.nixsolutions.dao.impl.StudentDaoImpl;
import com.nixsolutions.dao.impl.StudentGroupDaoImpl;
import com.nixsolutions.dao.impl.SubjectDaoImpl;
import com.nixsolutions.dao.impl.TermDaoImpl;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.StudentGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataBaseApp {
    private final static Logger LOG = LogManager.getLogger(DataBaseApp.class.getName());

    public static void main(String[] args) {
	TermDaoImpl term = DaoFactory.getTerm();
	StatusDaoImpl status = DaoFactory.getStatus();
	JournalDaoImpl journal = DaoFactory.getJournal();
	StudentDaoImpl student = DaoFactory.getStudent();
	StudentGroupDaoImpl studentGroup = DaoFactory.getStudentGroup();
	SubjectDaoImpl subject = DaoFactory.getSubject();
	term.create("I");
	studentGroup.create("310");
	studentGroup.create("311");
	studentGroup.create("312");
	studentGroup.create("313");
	studentGroup.create("316");
	studentGroup.delete(new StudentGroup(3, "312"));
	status.create("active");
	status.create("inactive");
	subject.create("Math", 1);
	subject.create("Physics", 1);
	subject.create("English", 1);
	student.create("Alex", "Ivanov", Date.valueOf("1990-01-01"), Date.valueOf("2007-09-01"), 1, 1, 1);
	student.create("Petr", "Petrov", Date.valueOf("1991-05-07"), Date.valueOf("2008-09-01"), 1, 1, 1);
	student.create("Victor", "Sergeev", Date.valueOf("1985-09-17"), Date.valueOf("2005-09-01"), 2, 1, 2);
	student.create("Victor", "Lastochkin", Date.valueOf("1991-01-02"), Date.valueOf("2007-09-01"), 2, 1, 1);
	student.create("Sergey", "Korobov", Date.valueOf("1991-01-05"), Date.valueOf("2007-09-01"), 4, 1, 1);
	student.update(
		new Student(2, "Alexandr", "Petrov", Date.valueOf("1991-05-07"), Date.valueOf("2008-09-01"), 1, 1, 1));
	journal.create(2, 1, "5");
	journal.create(2, 2, "4");
	journal.create(3, 1, "4");
	journal.create(3, 3, "3");
	LOG.info("Tables are filled");
	subject.getAll();
	student.getStudentsByName("Victor", "Sergeev");
	journal.getJournalByRate("5");
	LOG.info("Info is retrieved");
    }
}
