package com.nixsolutions.studentgrade.app;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.GradeDAO;
import com.nixsolutions.studentgrade.dao.JournalDAO;
import com.nixsolutions.studentgrade.dao.RoleDAO;
import com.nixsolutions.studentgrade.dao.StatusDAO;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.dao.StudentGroupDAO;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.dao.UserDAO;
import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.entity.User;

public class DAOApp {

	private final static Logger LOG = LogManager.getLogger(DAOApp.class.getName());

	public static void main(String[] args) {
		GradeDAO grade = DAOFactory.getGrade();
		Grade gradeNew = new Grade();
		String[] gradeNames = new String[] { "Fail", "Unsatisfactory", "Satisfactory", "Good", "Excellent" };
		for (String g : gradeNames) {
			gradeNew.setGradeName(g);
			grade.createGrade(gradeNew);
		}
		LOG.info("=====Table grade is filled=====");
		LOG.debug("List of grades:");
		for (Grade g : grade.findAllGrades())
			LOG.debug(g.getGradeId() + " " + g.getGradeName());

		StudentGroupDAO group = DAOFactory.getStudentGroup();
		StudentGroup groupNew = new StudentGroup();
		String[] groupNames = new String[] { "KI-2014", "PI-2015", "KI-2015" };
		for (String sg : groupNames) {
			groupNew.setGroupName(sg);
			group.createStudentGroup(groupNew);
		}
		LOG.info("=====Table student_group is filled=====");
		LOG.debug("List of groups:");
		for (StudentGroup sg : group.findAllStudentGroups())
			LOG.debug(sg.getGroupId() + " " + sg.getGroupName());

		StatusDAO status = DAOFactory.getStatus();
		Status statusNew = new Status();
		String[] statusNames = new String[] { "Active", "Academic leave", "Expelled" };
		for (String s : statusNames) {
			statusNew.setStatusName(s);
			status.createStatus(statusNew);
		}
		LOG.info("=====Table status is filled=====");
		LOG.debug("List of statuses:");
		for (Status s : status.findAllStatuses())
			LOG.debug(s.getStatusId() + " " + s.getStatusName());

		TermDAO term = DAOFactory.getTerm();
		Term termNew = new Term();
		String[] termNames = new String[] { "Autumn-2014", "Spring-2015", "Autumn-2015", "Spring-2016" };
		for (String t : termNames) {
			termNew.setTermName(t);
			term.createTerm(termNew);
		}
		LOG.info("=====Table term is filled=====");
		LOG.debug("List of terms:");
		for (Term t : term.findAllTerms())
			LOG.debug(t.getTermId() + " " + t.getTermName());

		SubjectDAO subject = DAOFactory.getSubject();
		Subject subjectNew = new Subject();
		subjectNew.setSubjectName("Mathematics");
		subjectNew.setTerm(term.findTermById(Long.valueOf(1)));
		subject.createSubject(subjectNew);
		subjectNew.setSubjectName("Engineering");
		subjectNew.setTerm(term.findTermById(Long.valueOf(1)));
		subject.createSubject(subjectNew);
		subjectNew.setSubjectName("Ethics");
		subjectNew.setTerm(term.findTermById(Long.valueOf(1)));
		subject.createSubject(subjectNew);
		subjectNew.setSubjectName("Philosophy");
		subjectNew.setTerm(term.findTermById(Long.valueOf(2)));
		subject.createSubject(subjectNew);
		subjectNew.setSubjectName("Information Systems");
		subjectNew.setTerm(term.findTermById(Long.valueOf(2)));
		subject.createSubject(subjectNew);
		subjectNew.setSubjectName("Software Engineering");
		subjectNew.setTerm(term.findTermById(Long.valueOf(2)));
		subject.createSubject(subjectNew);
		subjectNew.setSubjectName("Statistics");
		subjectNew.setTerm(term.findTermById(Long.valueOf(3)));
		subject.createSubject(subjectNew);
		subjectNew.setSubjectName("Computer Science");
		subjectNew.setTerm(term.findTermById(Long.valueOf(3)));
		subject.createSubject(subjectNew);
		subjectNew.setSubjectName("Electrical and Electronic Engineering");
		subjectNew.setTerm(term.findTermById(Long.valueOf(3)));
		subject.createSubject(subjectNew);
		LOG.info("=====Table subject is filled=====");
		LOG.debug("List of subjects:");
		for (Subject sub : subject.findAllSubjects())
			LOG.debug(sub.getSubjectId() + " " + sub.getSubjectName() + " " + sub.getTerm().getTermId());

		StudentDAO student = DAOFactory.getStudent();
		Student studentNew = new Student();
		studentNew.setFirstName("John");
		studentNew.setLastName("Smith");
		studentNew.setGroup(group.findStudentGroupById(Long.valueOf(1)));
		studentNew.setAdmissionDate(Date.valueOf("2014-09-01"));
		studentNew.setStatus(status.findStatusById(Long.valueOf(1)));
		studentNew.setTerm(term.findTermById(Long.valueOf(1)));
		student.createStudent(studentNew);
		studentNew.setFirstName("Tom");
		studentNew.setLastName("Foster");
		studentNew.setGroup(group.findStudentGroupById(Long.valueOf(1)));
		studentNew.setAdmissionDate(Date.valueOf("2014-09-01"));
		studentNew.setStatus(status.findStatusById(Long.valueOf(2)));
		studentNew.setTerm(term.findTermById(Long.valueOf(1)));
		student.createStudent(studentNew);
		studentNew.setFirstName("Kate");
		studentNew.setLastName("McLain");
		studentNew.setGroup(group.findStudentGroupById(Long.valueOf(1)));
		studentNew.setAdmissionDate(Date.valueOf("2014-09-01"));
		studentNew.setStatus(status.findStatusById(Long.valueOf(3)));
		studentNew.setTerm(term.findTermById(Long.valueOf(1)));
		student.createStudent(studentNew);
		studentNew.setFirstName("Julie");
		studentNew.setLastName("West");
		studentNew.setGroup(group.findStudentGroupById(Long.valueOf(2)));
		studentNew.setAdmissionDate(Date.valueOf("2014-09-01"));
		studentNew.setStatus(status.findStatusById(Long.valueOf(1)));
		studentNew.setTerm(term.findTermById(Long.valueOf(2)));
		student.createStudent(studentNew);
		studentNew.setFirstName("Jane");
		studentNew.setLastName("Reid");
		studentNew.setGroup(group.findStudentGroupById(Long.valueOf(2)));
		studentNew.setAdmissionDate(Date.valueOf("2014-09-01"));
		studentNew.setStatus(status.findStatusById(Long.valueOf(1)));
		studentNew.setTerm(term.findTermById(Long.valueOf(2)));
		student.createStudent(studentNew);
		studentNew.setFirstName("Craig");
		studentNew.setLastName("Cambell");
		studentNew.setGroup(group.findStudentGroupById(Long.valueOf(3)));
		studentNew.setAdmissionDate(Date.valueOf("2015-09-01"));
		studentNew.setStatus(status.findStatusById(Long.valueOf(1)));
		studentNew.setTerm(term.findTermById(Long.valueOf(3)));
		student.createStudent(studentNew);
		studentNew.setFirstName("Steve");
		studentNew.setLastName("Edwards");
		studentNew.setGroup(group.findStudentGroupById(Long.valueOf(3)));
		studentNew.setAdmissionDate(Date.valueOf("2015-09-01"));
		studentNew.setStatus(status.findStatusById(Long.valueOf(1)));
		studentNew.setTerm(term.findTermById(Long.valueOf(3)));
		student.createStudent(studentNew);
		LOG.info("=====Table student is filled=====");
		LOG.debug("List of students:");
		for (Student st : student.findAllStudents())
			LOG.debug(st.getStudentId() + " " + st.getFirstName() + " " + st.getLastName() + " "
					+ st.getGroup().getGroupId() + " " + st.getAdmissionDate() + " " + st.getStatus().getStatusId()
					+ " " + st.getTerm().getTermId());

		JournalDAO journal = DAOFactory.getJournal();
		Journal journalNew = new Journal();
		journalNew.setStudent(student.findStudentById(Long.valueOf(1)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(1)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(5)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(1)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(2)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(5)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(1)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(3)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(2)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(1)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(2)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(2)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(5)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(2)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(3)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(3)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(1)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(2)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(3)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(2)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(1)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(3)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(3)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(1)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(4)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(4)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(5)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(4)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(5)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(4)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(6)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(3)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(5)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(4)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(5)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(5)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(5)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(6)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(5)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(6)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(7)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(6)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(8)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(3)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(6)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(9)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(7)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(7)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(5)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(7)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(8)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(5)));
		journal.createJournal(journalNew);
		journalNew.setStudent(student.findStudentById(Long.valueOf(7)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(9)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(5)));
		journal.createJournal(journalNew);
		LOG.info("=====Table journal is filled=====");
		LOG.debug("List of journals:");
		for (Journal j : journal.findAllJournals())
			LOG.debug(j.getJournalId() + " " + j.getStudent().getStudentId() + " " + j.getSubject().getSubjectId() + " "
					+ j.getGrade().getGradeId());

		RoleDAO role = DAOFactory.getRole();
		Role roleNew = new Role();
		String[] roleNames = new String[] { "admin", "manager" };
		for (String g : roleNames) {
			roleNew.setRoleName(g);
			role.createRole(roleNew);
		}
		LOG.info("=====Table role is filled=====");
		LOG.debug("List of roles:");
		for (Role r : role.findAllRoles())
			LOG.debug(r.getRoleId() + " " + r.getRoleName());

		UserDAO user = DAOFactory.getUser();
		User userNew = new User();
		userNew.setLogin("admin");
		userNew.setPassword("admin");
		userNew.setEmail("admin@mail.com");
		userNew.setRole(role.findRoleById(Long.valueOf(1)));
		user.createUser(userNew);
		userNew.setLogin("manager");
		userNew.setPassword("manager");
		userNew.setEmail("manager@mail.com");
		userNew.setRole(role.findRoleById(Long.valueOf(2)));
		user.createUser(userNew);
		LOG.info("=====Table user is filled=====");
		LOG.debug("List of users:");
		for (User u : user.findAllUsers())
			LOG.debug(u.getUserId() + " " + u.getLogin() + " " + u.getPassword() + " " + u.getEmail() + " "
					+ u.getRole().getRoleId());
		}	
}
