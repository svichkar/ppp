package com.nixsolutions;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.Journal;
import com.nixsolutions.dao.Status;
import com.nixsolutions.dao.Student;
import com.nixsolutions.dao.StudentGroup;
import com.nixsolutions.dao.Subject;
import com.nixsolutions.dao.Term;

public class DataBaseApp {
    private final static Logger LOG = LogManager.getLogger(DataBaseApp.class.getName());

    public static void main(String[] args) {
	BasicConfigurator.configure();
	Term term = DAOFactory.getTerm();
	Status status = DAOFactory.getStatus();
	Journal journal = DAOFactory.getJournal();
	Student student = DAOFactory.getStudent();
	StudentGroup studentGroup = DAOFactory.getStudentGroup();
	Subject subject = DAOFactory.getSubject();
	try {
	    term.insert(new String[]{"term_alias"}, new String[]{"I"});
	    studentGroup.insert(new String[]{"group_name"}, new String[]{"310"});
	    studentGroup.insert(new String[]{"group_name"}, new String[]{"311"});
	    studentGroup.insert(new String[]{"group_name"}, new String[]{"312"});
	    studentGroup.insert(new String[]{"group_name"}, new String[]{"313"});
	    studentGroup.insert(new String[]{"group_name"}, new String[]{"316"});
	    studentGroup.delete("group_name = '312'");
	    status.insert(new String[]{"status_name"}, new String[]{"active"});
	    status.insert(new String[]{"status_name"}, new String[]{"inactive"});
	    subject.insert(new String[]{"subject_name", "term_id"}, new String[]{"Math", "1"} );
	    subject.insert(new String[]{"subject_name", "term_id"}, new String[]{"Physics", "1"} );
	    subject.insert(new String[]{"subject_name", "term_id"}, new String[]{"English", "1"} );
	    student.insert(new String[]{"first_name", "last_name", "date_birthday", "date_entry", "student_group_id", "term_id", "status_id"}, new String[]{"Alex", "Ivanov", "1990-01-01", "2007-09-01", "1", "1", "1"});
	    student.insert(new String[]{"first_name", "last_name", "date_birthday", "date_entry", "student_group_id", "term_id", "status_id"}, new String[]{"Petr", "Petrov", "1991-05-07", "2008-09-01", "1", "1", "1"});
	    student.insert(new String[]{"first_name", "last_name", "date_birthday", "date_entry", "student_group_id", "term_id", "status_id"}, new String[]{"Victor", "Sergeev", "1985-09-17", "2005-09-01", "2", "1", "2"});
	    student.insert(new String[]{"first_name", "last_name", "date_birthday", "date_entry", "student_group_id", "term_id", "status_id"}, new String[]{"Victor", "Lastochkin", "1991-01-02", "2007-09-01", "2", "1", "1"});
	    student.insert(new String[]{"first_name", "last_name", "date_birthday", "date_entry", "student_group_id", "term_id", "status_id"}, new String[]{"Sergey", "Korobov", "1991-010-05", "2007-09-01", "4", "1", "1"});
	    student.update(new String[]{"first_name"}, new String[]{"Alexandr"}, "student_id = '2'");
	    journal.insert(new String[]{"student_id", "subject_id", "rate"}, new String[]{"2", "1", "5"});
	    journal.insert(new String[]{"student_id", "subject_id", "rate"}, new String[]{"2", "2", "4"});
	    journal.insert(new String[]{"student_id", "subject_id", "rate"}, new String[]{"3", "1", "4"});
	    journal.insert(new String[]{"student_id", "subject_id", "rate"}, new String[]{"3", "3", "3"});
	    LOG.info("Tables are filled");
	    subject.select(new String[]{"*"}, null);
	    student.select(new String[]{"first_name", "last_name", "date_entry"}, null);
	    journal.select(new String[]{"*"}, "rate IN ('5', '4')");
	    LOG.info("Info is retrieved");
	} catch (ClassNotFoundException e) {
	    LOG.error(e);
	    throw new RuntimeException(e);
	} catch (IOException e) {
	    LOG.error(e);
	    throw new RuntimeException(e);
	} catch (SQLException e) {
	    LOG.error(e);
	    throw new RuntimeException(e);
	}
    }
}
