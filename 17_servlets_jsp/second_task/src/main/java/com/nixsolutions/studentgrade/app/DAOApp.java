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
		Grade [] gradeArray = new Grade[] {new Grade (1, "Fail"),
				new Grade (2, "Unsatisfactory"),
				new Grade (3, "Satisfactory"),
				new Grade (4, "Good"),
				new Grade (5, "Excellent"),
				new Grade (6, "Super")};
		for (Grade g : gradeArray)
		grade.createGrade(g);
		Grade gradeTest = new Grade(6, "Test");
		grade.updateGrade(gradeTest);
		grade.deleteGrade(gradeTest);
		LOG.info("=====Table grade is filled=====");
		LOG.debug("Grade with id 5 is " + grade.findGradeById(5).getGradeName());
		LOG.debug("List of grades:");
		for (Grade g : grade.findAllGrades())
			LOG.debug(g.getGradeId() + " " + g.getGradeName());

		StudentGroupDAO group = DAOFactory.getStudentGroup();
		StudentGroup [] groupArray = new StudentGroup[] {new StudentGroup ((long) 1, "KI-2014"),
				new StudentGroup ((long) 2, "PI-2015"),
				new StudentGroup ((long) 3, "KI-2015"),
				new StudentGroup ((long) 4, "KI-2016")};
		for (StudentGroup sg : groupArray)
			group.createStudentGroup(sg);
		StudentGroup groupTest = new StudentGroup((long) 4, "Test");
		group.updateStudentGroup(groupTest);
		group.deleteStudentGroup(groupTest);
		LOG.info("=====Table student_group is filled=====");
		LOG.debug("Group with id 3 is " + group.findStudentGroupById(Long.valueOf(3)).getGroupName());
		LOG.debug("List of groups:");
		for (StudentGroup sg : group.findAllStudentGroups())
			LOG.debug(sg.getGroupId() + " " + sg.getGroupName());

		StatusDAO status = DAOFactory.getStatus();
		Status [] statusArray = new Status[] {new Status (1, "Active"),
				new Status (2, "Academic leave"),
				new Status (3, "Expelled"),
				new Status (4, "Non-active")};
		for (Status s : statusArray)
			status.createStatus(s);
		Status statusTest = new Status(4, "Test");
		status.updateStatus(statusTest);
		status.deleteStatus(statusTest);
		LOG.info("=====Table status is filled=====");
		LOG.debug("Status with id 3 is " + status.findStatusById(3).getStatusName());
		LOG.debug("List of statuses:");
		for (Status s : status.findAllStatuses())
			LOG.debug(s.getStatusId() + " " + s.getStatusName());

		TermDAO term = DAOFactory.getTerm();
		Term [] termArray = new Term[] {new Term ((long) 1, "Autumn-2014"),
				new Term ((long) 2, "Spring-2015"),
				new Term ((long) 3, "Autumn-2015"),
				new Term ((long) 4, "Spring-2016"),
				new Term ((long) 5, "Autumn-2016")};
		for (Term t : termArray)
			term.createTerm(t);
		Term termTest = new Term((long) 5, "Test");
		term.updateTerm(termTest);
		term.deleteTerm(termTest);
		LOG.info("=====Table term is filled=====");
		LOG.debug("Term with id 3 is " + term.findTermById(Long.valueOf(3)).getTermName());
		LOG.debug("List of terms:");
		for (Term t : term.findAllTerms())
			LOG.debug(t.getTermId() + " " + t.getTermName());

		SubjectDAO subject = DAOFactory.getSubject();
		Subject [] subjectArray = new Subject[] {new Subject ((long) 1, "Mathematics", (long) 1),
				new Subject ((long) 2, "Engineering", (long) 1),
				new Subject ((long) 3, "Ethics", (long) 1),
				new Subject ((long) 4, "Philosophy", (long) 2),
				new Subject ((long) 5, "Information Systems", (long) 2),
				new Subject ((long) 6, "Software Engineering", (long) 2),
				new Subject ((long) 7, "Statistics", (long) 3),
				new Subject ((long) 8, "Computer Science", (long) 3),
				new Subject ((long) 9, "Electrical and Electronic Engineering", (long) 3),
				new Subject ((long) 10, "Psychology", (long) 3)};
		for (Subject sub : subjectArray)
			subject.createSubject(sub);
		Subject subjectTest = new Subject((long) 10, "Test", (long) 3);
		subject.updateSubject(subjectTest);
		subject.deleteSubject(subjectTest);
		LOG.info("=====Table subject is filled=====");
		LOG.debug("Subject with id 7 is " + subject.findSubjectById(Long.valueOf(7)).getSubjectName());
		LOG.debug("List of subjects:");
		for (Subject sub : subject.findAllSubjects())
			LOG.debug(sub.getSubjectId() + " " + sub.getSubjectName() + " " + sub.getTermId());

		StudentDAO student = DAOFactory.getStudent();
		Student [] studentArray = new Student[] {new Student (Long.valueOf(1), "John", "Smith", (long) 1, Date.valueOf("2014-09-01"), 1, (long) 1),
				new Student ((long) 2, "Tom", "Foster", (long) 1, Date.valueOf("2014-09-01"), 2, (long) 1),
				new Student ((long) 3, "Kate", "McLain", (long) 1, Date.valueOf("2014-09-01"), 3, (long) 1),
				new Student ((long) 4, "Julie", "West", (long) 2, Date.valueOf("2014-09-01"), 1, (long) 2),
				new Student ((long) 5, "Jane", "Reid", (long) 2, Date.valueOf("2014-09-01"), 1, (long) 2),
				new Student ((long) 6, "Craig", "Cambell", (long) 3, Date.valueOf("2015-09-01"), 1, (long) 3),
				new Student ((long) 7, "Steve", "Edwards", (long) 3, Date.valueOf("2015-09-01"), 1, (long) 3),
				new Student ((long) 8, "Mark", "Grey", (long) 1, Date.valueOf("2014-09-01"), 1, (long) 2)};
		for (Student st : studentArray)
			student.createStudent(st);
		Student studentTest = new Student((long) 8, "Mark", "Test", (long) 1, Date.valueOf("2014-09-01"), 1, (long) 2);
		student.updateStudent(studentTest);
		student.deleteStudent(studentTest);
		LOG.info("=====Table student is filled=====");
		LOG.debug("Student with id 7 is " + student.findStudentById(Long.valueOf(7)).getFirstName() + " "
				+ student.findStudentById(Long.valueOf(7)).getLastName());
		LOG.debug("List of students:");
		for (Student st : student.findAllStudents())
			LOG.debug(st.getStudentId() + " " + st.getFirstName() + " " + st.getLastName() + " " + st.getGroupId() + " "
					+ st.getAdmissionDate() + " " + st.getStatusId() + " " + st.getTermId());

		JournalDAO journal = DAOFactory.getJournal();
		Journal [] journalArray = new Journal[] {new Journal ((long) 1, (long) 1, (long) 1, 5),
				new Journal ((long) 2, (long) 1, (long) 2, 5),
				new Journal ((long) 3, (long) 1, (long) 3, 4),
				new Journal ((long) 4, (long) 2, (long) 1, 4),
				new Journal ((long) 5, (long) 2, (long) 2, 5),
				new Journal ((long) 6, (long) 2, (long) 3, 4),
				new Journal ((long) 7, (long) 3, (long) 1, 2),
				new Journal ((long) 8, (long) 3, (long) 2, 1),
				new Journal ((long) 9, (long) 3, (long) 3, 1),
				new Journal ((long) 10, (long) 4, (long) 4, 5),
				new Journal ((long) 11, (long) 4, (long) 5, 4),
				new Journal ((long) 12, (long) 4, (long) 6, 3),
				new Journal ((long) 13, (long) 5, (long) 4, 4),
				new Journal ((long) 14, (long) 5, (long) 5, 4),
				new Journal ((long) 15, (long) 5, (long) 6, 5),
				new Journal ((long) 16, (long) 6, (long) 7, 4),
				new Journal ((long) 17, (long) 6, (long) 8, 3),
				new Journal ((long) 18, (long) 6, (long) 9, 4),
				new Journal ((long) 19, (long) 7, (long) 7, 5),
				new Journal ((long) 20, (long) 7, (long) 8, 5),
				new Journal ((long) 21, (long) 7, (long) 9, 5),
				new Journal ((long) 22, (long) 7, (long) 6, 4)};
		for (Journal j : journalArray)
			journal.createJournal(j);
		Journal journalTest = new Journal((long) 22, (long) 7, (long) 6, 5);
		journal.updateJournal(journalTest);
		journal.deleteJournal(journalTest);
		LOG.info("=====Table journal is filled=====");
		LOG.debug("Journal with id 10 is: student_id=" + journal.findJournalById(Long.valueOf(10)).getStudentId() + ", subject_id="
				+ journal.findJournalById(Long.valueOf(10)).getSubjectId() + ", grade_id="
				+ +journal.findJournalById(Long.valueOf(10)).getGradeId());
		LOG.debug("List of journals:");
		for (Journal j : journal.findAllJournals())
			LOG.debug(j.getJournalId() + " " + j.getStudentId() + " " + j.getSubjectId() + " " + j.getGradeId());
		RoleDAO role = DAOFactory.getRole();
		role.createRole(new Role (1, "admin"));
		role.createRole(new Role (2, "manager"));
		LOG.info("=====Table role is filled=====");
		UserDAO user = DAOFactory.getUser();
		user.createUser(new User (1, "admin", "admin", "admin@mail.com", 1));
		user.createUser(new User (2, "manager", "manager", "manager@mail.com", 2));
		LOG.info("=====Table user is filled=====");	
	}

}
