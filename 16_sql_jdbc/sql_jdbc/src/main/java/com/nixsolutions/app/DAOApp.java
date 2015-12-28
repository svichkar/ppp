package com.nixsolutions.app;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.impl.GradeDAOImpl;
import com.nixsolutions.dao.impl.JournalDAOImpl;
import com.nixsolutions.dao.impl.StatusDAOImpl;
import com.nixsolutions.dao.impl.StudentDAOImpl;
import com.nixsolutions.dao.impl.StudentGroupDAOImpl;
import com.nixsolutions.dao.impl.SubjectDAOImpl;
import com.nixsolutions.dao.impl.TermDAOImpl;
import com.nixsolutions.entity.Grade;
import com.nixsolutions.entity.Journal;
import com.nixsolutions.entity.Status;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.StudentGroup;
import com.nixsolutions.entity.Subject;
import com.nixsolutions.entity.Term;

public class DAOApp {

	private final static Logger LOG = LogManager.getLogger(DAOApp.class.getName());

	public static void main(String[] args) {
		GradeDAOImpl grade = DAOFactory.getGrade();
		grade.createGrade(1, "Fail");
		grade.createGrade(2, "Unsatisfactory");
		grade.createGrade(3, "Satisfactory");
		grade.createGrade(4, "Good");
		grade.createGrade(5, "Excellent");
		grade.createGrade(6, "Super");
		Grade gradeTest = new Grade(6, "Test");
		grade.updateGrade(gradeTest);
		grade.deleteGrade(gradeTest);
		LOG.info("=====Table grade is filled=====");
		LOG.debug("Grade with id 5 is " + grade.findGradeById(5).getGradeName());
		LOG.debug("List of grades:");
		for (Grade g : grade.findAllGrades())
			LOG.debug(g.getGradeId() + " " + g.getGradeName());

		StudentGroupDAOImpl group = DAOFactory.getStudentGroup();
		group.createStudentGroup(1, "KI-2014");
		group.createStudentGroup(2, "PI-2015");
		group.createStudentGroup(3, "KI-2015");
		group.createStudentGroup(4, "KI-2016");
		StudentGroup groupTest = new StudentGroup(4, "Test");
		group.updateStudentGroup(groupTest);
		group.deleteStudentGroup(groupTest);
		LOG.info("=====Table student_group is filled=====");
		LOG.debug("Group with id 3 is " + group.findStudentGroupById(3).getGroupName());
		LOG.debug("List of groups:");
		for (StudentGroup sg : group.findAllStudentGroups())
			LOG.debug(sg.getGroupId() + " " + sg.getGroupName());

		StatusDAOImpl status = DAOFactory.getStatus();
		status.createStatus(1, "Active");
		status.createStatus(2, "Academic leave");
		status.createStatus(3, "Expelled");
		status.createStatus(4, "Non-active");
		Status statusTest = new Status(4, "Test");
		status.updateStatus(statusTest);
		status.deleteStatus(statusTest);
		LOG.info("=====Table status is filled=====");
		LOG.debug("Status with id 3 is " + status.findStatusById(3).getStatusName());
		LOG.debug("List of statuses:");
		for (Status s : status.findAllStatuses())
			LOG.debug(s.getStatusId() + " " + s.getStatusName());

		TermDAOImpl term = DAOFactory.getTerm();
		term.createTerm(1, "Autumn-2014");
		term.createTerm(2, "Spring-2015");
		term.createTerm(3, "Autumn-2015");
		term.createTerm(4, "Spring-2016");
		term.createTerm(5, "Autumn-2016");
		Term termTest = new Term(5, "Test");
		term.updateTerm(termTest);
		term.deleteTerm(termTest);
		LOG.info("=====Table term is filled=====");
		LOG.debug("Term with id 3 is " + term.findTermById(3).getTermName());
		LOG.debug("List of terms:");
		for (Term t : term.findAllTerms())
			LOG.debug(t.getTermId() + " " + t.getTermName());

		SubjectDAOImpl subject = DAOFactory.getSubject();
		subject.createSubject(1, "Mathematics", 1);
		subject.createSubject(2, "Engineering", 1);
		subject.createSubject(3, "Ethics", 1);
		subject.createSubject(4, "Philosophy", 2);
		subject.createSubject(5, "Information Systems", 2);
		subject.createSubject(6, "Software Engineering", 2);
		subject.createSubject(7, "Statistics", 3);
		subject.createSubject(8, "Computer Science", 3);
		subject.createSubject(9, "Electrical and Electronic Engineering", 3);
		subject.createSubject(10, "Psychology", 3);
		Subject subjectTest = new Subject(10, "Test", 3);
		subject.updateSubject(subjectTest);
		subject.deleteSubject(subjectTest);
		LOG.info("=====Table subject is filled=====");
		LOG.debug("Subject with id 7 is " + subject.findSubjectById(7).getSubjectName());
		LOG.debug("List of subjects:");
		for (Subject sub : subject.findAllSubjects())
			LOG.debug(sub.getSubjectId() + " " + sub.getSubjectName() + " " + sub.getTermId());

		StudentDAOImpl student = DAOFactory.getStudent();
		student.createStudent(1, "John", "Smith", 1, Date.valueOf("2014-09-01"), 1, 1);
		student.createStudent(2, "Tom", "Foster", 1, Date.valueOf("2014-09-01"), 2, 1);
		student.createStudent(3, "Kate", "McLain", 1, Date.valueOf("2014-09-01"), 3, 1);
		student.createStudent(4, "Julie", "West", 1, Date.valueOf("2014-09-01"), 1, 2);
		student.createStudent(5, "Jane", "Reid", 1, Date.valueOf("2014-09-01"), 1, 2);
		student.createStudent(6, "Craig", "Cambell", 1, Date.valueOf("2015-09-01"), 1, 3);
		student.createStudent(7, "Steve", "Edwards", 1, Date.valueOf("2015-09-01"), 1, 3);
		student.createStudent(8, "Mark", "Grey", 1, Date.valueOf("2014-09-01"), 1, 2);
		Student studentTest = new Student(8, "Mark", "Test", 1, Date.valueOf("2014-09-01"), 1, 2);
		student.updateStudent(studentTest);
		student.deleteStudent(studentTest);
		LOG.info("=====Table student is filled=====");
		LOG.debug("Student with id 7 is " + student.findStudentById(7).getFirstName() + " "
				+ student.findStudentById(7).getLastName());
		LOG.debug("List of students:");
		for (Student st : student.findAllStudents())
			LOG.debug(st.getStudentId() + " " + st.getFirstName() + " " + st.getLastName() + " " + st.getGroupId() + " "
					+ st.getAdmissionDate() + " " + st.getStatusId() + " " + st.getTermId());

		JournalDAOImpl journal = DAOFactory.getJournal();
		journal.createJournal(1, 1, 1, 5);
		journal.createJournal(2, 1, 2, 5);
		journal.createJournal(3, 1, 3, 4);
		journal.createJournal(4, 2, 1, 4);
		journal.createJournal(5, 2, 2, 5);
		journal.createJournal(6, 2, 3, 4);
		journal.createJournal(7, 3, 1, 2);
		journal.createJournal(8, 3, 2, 1);
		journal.createJournal(9, 3, 3, 1);
		journal.createJournal(10, 4, 4, 5);
		journal.createJournal(11, 4, 5, 4);
		journal.createJournal(12, 4, 6, 3);
		journal.createJournal(13, 5, 4, 4);
		journal.createJournal(14, 5, 5, 4);
		journal.createJournal(15, 5, 6, 5);
		journal.createJournal(16, 6, 7, 4);
		journal.createJournal(17, 6, 8, 3);
		journal.createJournal(18, 6, 9, 4);
		journal.createJournal(19, 7, 7, 5);
		journal.createJournal(20, 7, 8, 5);
		journal.createJournal(21, 7, 9, 5);
		journal.createJournal(22, 7, 6, 4);
		Journal journalTest = new Journal(22, 7, 6, 5);
		journal.updateJournal(journalTest);
		journal.deleteJournal(journalTest);
		LOG.info("=====Table journal is filled=====");
		LOG.debug("Journal with id 10 is: student_id=" + journal.findJournalById(10).getStudentId() + ", subject_id="
				+ journal.findJournalById(10).getSubjectId() + ", grade_id="
				+ +journal.findJournalById(10).getGradeId());
		LOG.debug("List of journals:");
		for (Journal j : journal.findAllJournals())
			LOG.debug(j.getJournalId() + " " + j.getStudentId() + " " + j.getSubjectId() + " " + j.getGradeId());

	}

}
