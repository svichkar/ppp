package com.nixsolutions.studentgrade.app;

import com.nixsolutions.studentgrade.dao.*;
import com.nixsolutions.studentgrade.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

/**
 * Created by svichkar on 12/22/2015.
 */
public class DaoDemo {

    private static final Logger LOG = LogManager.getLogger(DaoDemo.class);

    public static void main(String args[]) {

        StudentGradeDaoFactory daoFactory = new StudentGradeDaoFactory();

        //Grade DAO Demo:
        LOG.info("********* Grade DAO Demo started *********");
        GradeDao dao = daoFactory.getGradeDao();
        LOG.info("Grade DAO was fetched from DAO Factory");

        Grade grade1 = new Grade(1, "poor");
        Grade grade2 = new Grade(2, "fair");
        Grade grade3 = new Grade(3, "average");
        Grade grade4 = new Grade(4, "good");
        Grade grade5 = new Grade(5, "excellent");
        Grade grade6 = new Grade(6, "other");
        dao.create(grade1);
        dao.create(grade2);
        dao.create(grade3);
        dao.create(grade4);
        dao.create(grade5);
        dao.create(grade6);
        LOG.info("Created new Grade entities");

        grade1.setGradeName("very bad");
        dao.update(grade1);
        LOG.info("Updated existing Grade entity with new name value");

        boolean isDeleted = dao.delete(grade6);
        LOG.info("Delete operation returned '{}' value", isDeleted);

        List<Grade> list = dao.findAll();
        LOG.info("'findAll' operation returned list of all grades. list size  - {}", list.size());

        int id = 1;
        Grade foundGradeById = dao.findById(id);
        LOG.info("Grade entity with id - {}, name - '{}' was found by 'findById' operation (id = {})",
                foundGradeById.getGradeId(), foundGradeById.getGradeName(), id);
        LOG.info("********* Grade DAO Demo finished *********");

        //Term DAO Demo:
        LOG.info("********* Term DAO Demo started *********");
        TermDao termDao = daoFactory.getTermDao();
        LOG.info("Term DAO was fetched from DAO Factory");

        Term term1 = new Term(1, "first");
        Term term2 = new Term(2, "second");
        Term term3 = new Term(3, "third");

        termDao.create(term1);
        termDao.create(term2);
        termDao.create(term3);
        LOG.info("Created new Term entities");

        term3.setTermName("another");
        termDao.update(term3);
        LOG.info("Updated existing Term entity with new name value");

        boolean isTermDeleted = termDao.delete(term3);
        LOG.info("Delete operation returned '{}' value", isTermDeleted);

        List<Term> listTerm = termDao.findAll();
        LOG.info("'findAll' operation returned list of all terms. list size  - {}", list.size());

        int termId = 1;
        Term foundTermById = termDao.findById(termId);
        LOG.info("Grade entity with id - {}, name - '{}' was found by 'findById' operation (id = {})",
                foundTermById.getTermId(), foundTermById.getTermName(), termId);
        LOG.info("********* Term DAO Demo finished *********");

        //Subject DAO Demo:
        LOG.info("********* Subject DAO Demo started *********");
        SubjectDao subjectDao = daoFactory.getSubjectDao();
        LOG.info("Subject DAO was fetched from DAO Factory");

        Subject sub1 = new Subject(1, "Java", 2);
        Subject sub2 = new Subject(2, "Mathematics", 1);
        Subject sub3 = new Subject(3, "Physics", 2);
        Subject sub4 = new Subject(4, "Computing science", 1);
        Subject sub5 = new Subject(5, "Databases", 2);
        subjectDao.create(sub1);
        subjectDao.create(sub2);
        subjectDao.create(sub3);
        subjectDao.create(sub4);
        subjectDao.create(sub5);
        LOG.info("Created new Subject entities");

        sub5.setTermId(1);
        subjectDao.update(sub5);
        LOG.info("Updated existing Subject entity with new termId value");

        boolean isSubDeleted = subjectDao.delete(sub5);
        LOG.info("Delete operation returned '{}' value", isSubDeleted);

        List<Subject> listSub = subjectDao.findAll();
        LOG.info("'findAll' operation returned list of all subjects. list size  - {}", listSub.size());

        int subId = 1;
        Subject foundSubById = subjectDao.findById(subId);
        LOG.info("Subject entity with id - {}, name - '{}' was found by 'findById' operation (id = {})",
                foundSubById.getSubjectId(), foundSubById.getSubjectName(), subId);
        LOG.info("********* Subject DAO Demo finished *********");

        //Status DAO Demo:
        LOG.info("********* Status DAO Demo started ********* ");
        StatusDao statusDao = daoFactory.getStatusDao();
        LOG.info("Status DAO was fetched from DAO Factory");

        Status status1 = new Status(1, "active");
        Status status2 = new Status(2, "vacation");
        Status status3 = new Status(3, "expelled");
        Status status4 = new Status(4, "graduated");
        Status status5 = new Status(5, "other");
        statusDao.create(status1);
        statusDao.create(status2);
        statusDao.create(status3);
        statusDao.create(status4);
        statusDao.create(status5);
        LOG.info("Created new Status entities");

        status5.setStatusName("Maybe Wunderkinder");
        statusDao.update(status5);
        LOG.info("Updated existing Status entity with new statusName value");

        boolean isStatusDeleted = statusDao.delete(status5);
        LOG.info("Delete operation returned '{}' value", isStatusDeleted);

        List<Status> listStatus = statusDao.findAll();
        LOG.info("'findAll' operation returned list of all statuses. list size  - {}", listStatus.size());

        int statusId = 2;
        Status foundStatusById = statusDao.findById(statusId);
        LOG.info("Status entity with statusId - {}, statusName - '{}' was found by 'findById' operation (id = {})",
                foundStatusById.getStatusId(), foundStatusById.getStatusName(), statusId);
        LOG.info("********* Status DAO Demo finished *********");

        //Student Group DAO Demo:
        LOG.info("********* Student Group DAO Demo started ********* ");
        StudentGroupDao studentGroupDao = daoFactory.getStudentGroupDao();
        LOG.info("Student Group DAO was fetched from DAO Factory");

        StudentGroup group1 = new StudentGroup(1, "java 15-1");
        StudentGroup group2 = new StudentGroup(2, "java 15-2");
        StudentGroup group3 = new StudentGroup(3, "java 15-3");
        StudentGroup group4 = new StudentGroup(4, "java 15-4");
        StudentGroup group5 = new StudentGroup(5, "java 15-5");
        studentGroupDao.create(group1);
        studentGroupDao.create(group2);
        studentGroupDao.create(group3);
        studentGroupDao.create(group4);
        studentGroupDao.create(group5);
        LOG.info("Created new Student Group entities");

        group5.setGroupName("the best");
        studentGroupDao.update(group5);
        LOG.info("Updated existing Student Group entity with new groupName value");

        boolean isGroupDeleted = studentGroupDao.delete(group5);
        LOG.info("Delete operation returned '{}' value", isGroupDeleted);

        List<StudentGroup> listGroup = studentGroupDao.findAll();
        LOG.info("'findAll' operation returned list of all groups. list size  - {}", listGroup.size());

        int groupId = 2;
        StudentGroup foundGroupById = studentGroupDao.findById(groupId);
        LOG.info("Student Group entity with groupId - {}, groupName - '{}' was found by 'findById' operation (id = {})",
                foundGroupById.getGroupId(), foundGroupById.getGroupName(), groupId);
        LOG.info("********* Student Group DAO Demo finished *********");


        //Student DAO Demo:
        LOG.info("********* Student DAO Demo started *********");
        StudentDao studentDao = daoFactory.getStudentDao();
        LOG.info("Student DAO was fetched from DAO Factory");

        Student student1 = new Student(1, "Alex", "Ross", 1, Date.valueOf("2015-03-25"), 1, 1);
        Student student2 = new Student(2, "Nick", "Lynn", 2, Date.valueOf("2015-11-11"), 2, 2);
        Student student3 = new Student(3, "Andrew", "Bullock", 3, Date.valueOf("2015-05-04"), 2, 1);
        Student student4 = new Student(4, "Jordan", "Mcmillan", 1, Date.valueOf("2015-02-06"), 2, 2);
        Student student5 = new Student(5, "Marlon", "Coleman", 2, Date.valueOf("2015-07-26"), 2, 1);
        studentDao.create(student1);
        studentDao.create(student2);
        studentDao.create(student3);
        studentDao.create(student4);
        studentDao.create(student5);
        LOG.info("Created new Student entities");

        student1.setTermId(2);
        studentDao.update(student1);
        LOG.info("Updated existing Student entity with new termId value");

        boolean isStudentDeleted = studentDao.delete(student2);
        LOG.info("Delete operation returned '{}' value", isStudentDeleted);

        List<Student> listStudent = studentDao.findAll();
        LOG.info("'findAll' operation returned list of all students. list size  - {}", listStudent.size());

        int studentId = 3;
        Student foundStudentById = studentDao.findById(id);
        LOG.info("Student entity with studentId - {}, firstName - '{}', lastName - '{}'was found by 'findById' operation",
                foundStudentById.getStudentId(), foundStudentById.getFirstName(), foundStudentById.getLastName());
        LOG.info("********* Student DAO Demo finished *********");


        //Journal DAO Demo:
        LOG.info("********* Journal DAO Demo started ********* ");
        JournalDao journalDao = daoFactory.getJournalDao();
        LOG.info("Journal DAO was fetched from DAO Factory");

        Journal journal1 = new Journal(1, 1, 3, 3);
        Journal journal2 = new Journal(2, 1, 2, 5);
        Journal journal3 = new Journal(3, 3, 3, 4);
        Journal journal4 = new Journal(4, 3, 2, 5);
        Journal journal5 = new Journal(5, 3, 1, 5);
        journalDao.create(journal1);
        journalDao.create(journal2);
        journalDao.create(journal3);
        journalDao.create(journal4);
        journalDao.create(journal5);
        LOG.info("Created new Journal entities");

        journal1.setGradeId(4);
        journalDao.update(journal1);
        LOG.info("Updated existing Journal entity with new gradeId value - '{}'",journal1.getGradeId());

        boolean isJournalDeleted = journalDao.delete(journal2);
        LOG.info("Delete operation returned '{}' value",isJournalDeleted);

        List<Journal> listJournal = journalDao.findAll();
        LOG.info("'findAll' operation returned list of all journals. list size  - {}", listJournal.size());

        int journalId = 3;
        Journal foundJournalById = journalDao.findById(journalId);
        LOG.info("Journal entity with journalId - {}, studentId - {}, subjectId - {}, gradeId - {}"+
                        "was found by 'findById' operation (id = {})",
                foundJournalById.getJournalId(),foundJournalById.getStudentId(),
                foundJournalById.getSubjectId(),foundJournalById.getGradeId(),journalId);
        LOG.info("********* Journal DAO Demo finished *********");
    }
}
