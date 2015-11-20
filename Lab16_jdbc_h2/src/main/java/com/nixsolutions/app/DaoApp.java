package com.nixsolutions.app;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.util.ConnectionManager;
import com.nixsolutions.dao.*;
import com.nixsolutions.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.impl.H2DaoFactory;

public class DaoApp {
	private final static Logger LOG = LogManager.getLogger(DaoApp.class.getName());

	public static void main(String[] args) throws ClassNotFoundException {
		DaoFactory daoFactory = new H2DaoFactory();
		try {
			System.out.println("*****Rate*****");
			RateDao rateDao = daoFactory.getRateDao();
			rateDao.create('E');
			rateDao.create('D');
			rateDao.create('C');
			rateDao.create('B');
			rateDao.create('A');
			List<Rate> rates = rateDao.getAll();
			for (Rate rt : rates) {
				System.out.println(rt.getId() + "|" + rt.getRateValue());
			}
			rates.get(0).setStatusName('Z');
			rateDao.update(rates.get(0));
			System.out.println("Updated");
			Rate rate = rateDao.getByRateId(1);
			System.out.println(rate.getId() + "|" + rate.getRateValue());

			System.out.println("*****Term*****");
			TermDao termDao = daoFactory.getTermDao();
			termDao.create("I");
			termDao.create("II");
			termDao.create("III");
			termDao.create("IV");
			List<Term> terms = termDao.getAll();
			for (Term term : terms) {
				System.out.println(term.getId() + "|" + term.getAlias());
			}
			System.out.println("Deleted");
			termDao.delete(terms.get(3));
			terms = termDao.getAll();
			for (Term term : terms) {
				System.out.println(term.getId() + "|" + term.getAlias());
			}
			System.out.println("*****Student Group*****");
			StudentGroupDao studentGroupDao = daoFactory.getStudentGroupDao();
			studentGroupDao.create("CSN-01");
			studentGroupDao.create("CSN-02");
			studentGroupDao.create("SP-01");
			studentGroupDao.create("SP-02");
			List<StudentGroup> studentGroups = studentGroupDao.getAll();
			for (StudentGroup studentGroup : studentGroups) {
				System.out.println(studentGroup.getId() + "|" + studentGroup.getGroupName());
			}
			System.out.println("*****Status*****");
			StatusDao statusDao = daoFactory.getStatusDao();
			statusDao.create("Active");
			statusDao.create("Vacation");
			statusDao.create("Expelled");
			List<Status> statuses = statusDao.getAll();
			for (Status status : statuses) {
				System.out.println(status.getId() + "|" + status.getStatusName());
			}
			System.out.println("*****Subject*****");
			SubjectDao subjectDao = daoFactory.getSubjectDao();
			subjectDao.create("Test Predmet 1", 1);
			subjectDao.create("Test Predmet 2", 1);
			subjectDao.create("Test Predmet 3", 1);
			subjectDao.create("Test Predmet 4", 2);
			subjectDao.create("Test Predmet 5", 2);
			subjectDao.create("Test Predmet 6", 3);
			List<Subject> subjects = subjectDao.getAll();
			for (Subject subject : subjects) {
				System.out.println(subject.getId() + "|" + subject.getName() + "|" + subject.getTermId());
			}
			System.out.println("*****Student*****");
			StudentDao studentDao = daoFactory.getStudentDao();
			studentDao.create("Petr", "Ivanov", Date.valueOf("1990-02-01"), Date.valueOf("2005-06-15"), 1, 1, 1);
			studentDao.create("Ivan", "Petrov", Date.valueOf("1991-06-15"), Date.valueOf("2005-06-21"), 1, 1, 1);
			studentDao.create("Serg", "Pypkin", Date.valueOf("1990-02-01"), Date.valueOf("2005-06-15"), 2, 2, 1);
			studentDao.create("Anna", "Ivanova", Date.valueOf("1990-02-01"), Date.valueOf("2005-06-15"), 2, 2, 2);
			studentDao.create("Olga", "Petrova", Date.valueOf("1990-02-01"), Date.valueOf("2005-06-15"), 3, 3, 3);
			List<Student> students = studentDao.getAll();
			for (Student student : students) {
				System.out.println(student.getId() + "|" + student.getFirstName() + "|" + student.getLastName() + "|"
						+ student.getDateBirthday());
			}
			System.out.println("Search by Name");
			List<Student> studentsFinded = studentDao.getByStudentsByName("Ivan", "Petrov");
			for (Student student : studentsFinded) {
				System.out.println(student.getId() + "|" + student.getFirstName() + "|" + student.getLastName() + "|"
						+ student.getDateBirthday() + "|" + student.getStudentGroupId());
			}
			System.out.println("*****Journal*****");
			JournalDao journalDao = daoFactory.getJournalDao();
			journalDao.create(1, 1, 5);
			journalDao.create(1, 2, 5);
			journalDao.create(2, 2, 5);
			journalDao.create(2, 3, 5);
			journalDao.create(3, 2, 5);
			journalDao.create(3, 1, 5);
			journalDao.create(4, 5, 5);
			journalDao.create(4, 4, 5);
			journalDao.create(5, 6, 5);
			List<Journal> journals = journalDao.getAll();
			for (Journal journal : journals) {
				System.out.println(journal.getId() + "|" + journal.getStudentId() + "|" + journal.getSubjectId() + "|"
						+ journal.getRate());
			}
			System.out.println("*****Role*****");
			RoleDao roleDao = daoFactory.getRoleDao();
			roleDao.create("Admin");
			roleDao.create("Manager");
			List<Role> roles = roleDao.getAll();
			for (Role role : roles) {
				System.out.println(role.getId() + "|" + role.getRoleName());
			}
			System.out.println("*****User*****");
			UserDao userDao = daoFactory.getUserDao();
			userDao.create("Admin", "Admin", "test@test.com", 1);
			User user = userDao.getByUserName("Admin");
			System.out.println(user.getId() + "|" + user.getUserName() + "|" + user.getEmail());
		} catch (Exception e) {
			LOG.error(e, e);
		} finally {
			ConnectionManager.closeAllConnections();
		}
	}
}
