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

        DaoFactory daoFactory = new DaoFactory();

        //Grade DAO Demo:
        LOG.info("********* Grade DAO Demo started *********");
        GradeDao dao = daoFactory.getGradeDao();
        LOG.info("Grade DAO was fetched from DAO Factory");

        dao.create(new Grade("poor"));
        dao.create(new Grade("fair"));
        dao.create(new Grade("average"));
        dao.create(new Grade("good"));
        dao.create(new Grade("excellent"));
        dao.create(new Grade("other"));
        LOG.info("Created new Grade entities");

        List<Grade> listGrade = dao.findAll();
        LOG.info("'findAll' operation returned list of all grades. list size  - {}", listGrade.size());

        Grade test = listGrade.get(3);
        Long id = test.getGradeId();
        Grade foundGradeById = dao.findById(id);
        LOG.info("Grade entity with id - {}, name - '{}' was found by 'findById' operation (id = {})",
                foundGradeById.getGradeId(), foundGradeById.getGradeName(), id);

        test.setGradeName("very bad");
        dao.update(test);
        LOG.info("Updated existing Grade entity with new name value");

        Grade delete = listGrade.get(4);
        boolean isDeleted = dao.delete(delete);
        LOG.info("Delete operation returned '{}' value", isDeleted);
        LOG.info("********* Grade DAO Demo finished *********");

        //Term DAO Demo:
        LOG.info("********* Term DAO Demo started *********");
        TermDao termDao = daoFactory.getTermDao();
        LOG.info("Term DAO was fetched from DAO Factory");

        termDao.create(new Term("first"));
        termDao.create(new Term("second"));
        termDao.create(new Term("third"));
        LOG.info("Created new Term entities");

        List<Term> listTerm = termDao.findAll();
        LOG.info("'findAll' operation returned list of all terms. list size  - {}", listTerm.size());

        Term testTerm = listTerm.get(2);
        Long termId = testTerm.getTermId();
        Term foundTermById = termDao.findById(termId);
        LOG.info("Term entity with id - {}, name - '{}' was found by 'findById' operation (id = {})",
                foundTermById.getTermId(), foundTermById.getTermName(), termId);

        testTerm.setTermName("another");
        termDao.update(testTerm);
        LOG.info("Updated existing Term entity with new name value");

        Term deleteTerm = listTerm.get(2);
        boolean isTermDeleted = termDao.delete(deleteTerm);
        LOG.info("Delete operation returned '{}' value", isTermDeleted);
        LOG.info("********* Term DAO Demo finished *********");

        //Subject DAO Demo:
        LOG.info("********* Subject DAO Demo started *********");
        SubjectDao subjectDao = daoFactory.getSubjectDao();
        LOG.info("Subject DAO was fetched from DAO Factory");

        subjectDao.create(new Subject("Java", new Long(2)));
        subjectDao.create(new Subject("Mathematics", new Long(1)));
        subjectDao.create(new Subject("Physics", new Long(2)));
        subjectDao.create(new Subject("Computing science", new Long(1)));
        subjectDao.create(new Subject("Databases", new Long(2)));
        LOG.info("Created new Subject entities");

        List<Subject> listSub = subjectDao.findAll();
        LOG.info("'findAll' operation returned list of all subjects. list size  - {}", listSub.size());

        Subject testSub = listSub.get(3);
        Long subId = testSub.getSubjectId();
        Subject foundSubById = subjectDao.findById(subId);
        LOG.info("Subject entity with id - {}, name - '{}' was found by 'findById' operation (id = {})",
                foundSubById.getSubjectId(), foundSubById.getSubjectName(), subId);

        testSub.setTermId(new Long(1));
        subjectDao.update(testSub);
        LOG.info("Updated existing Subject entity with new termId value");

        Subject deleteSub = listSub.get(1);
        boolean isSubDeleted = subjectDao.delete(deleteSub);
        LOG.info("Delete operation returned '{}' value", isSubDeleted);
        LOG.info("********* Subject DAO Demo finished *********");

        //Status DAO Demo:
        LOG.info("********* Status DAO Demo started ********* ");
        StatusDao statusDao = daoFactory.getStatusDao();
        LOG.info("Status DAO was fetched from DAO Factory");

        statusDao.create( new Status("active"));
        statusDao.create(new Status("vacation"));
        statusDao.create( new Status("expelled"));
        statusDao.create(new Status("graduated"));
        statusDao.create(new Status("other"));
        LOG.info("Created new Status entities");

        List<Status> listStatus = statusDao.findAll();
        LOG.info("'findAll' operation returned list of all statuses. list size  - {}", listStatus.size());

        Status testStatus = listStatus.get(3);
        Long statusId = testStatus.getStatusId();
        Status foundStatusById = statusDao.findById(statusId);
        LOG.info("Status entity with statusId - {}, statusName - '{}' was found by 'findById' operation (id = {})",
                foundStatusById.getStatusId(), foundStatusById.getStatusName(), statusId);

        testStatus.setStatusName("Maybe Wunderkinder");
        statusDao.update(testStatus);
        LOG.info("Updated existing Status entity with new statusName value");

        Status deleteStatus = listStatus.get(3);
        boolean isStatusDeleted = statusDao.delete(deleteStatus);
        LOG.info("Delete operation returned '{}' value", isStatusDeleted);
        LOG.info("********* Status DAO Demo finished *********");

        //Student Group DAO Demo:
        LOG.info("********* Student Group DAO Demo started ********* ");
        StudentGroupDao studentGroupDao = daoFactory.getStudentGroupDao();
        LOG.info("Student Group DAO was fetched from DAO Factory");

        studentGroupDao.create(new StudentGroup("java 15-1"));
        studentGroupDao.create(new StudentGroup("java 15-2"));
        studentGroupDao.create(new StudentGroup("java 15-3"));
        studentGroupDao.create(new StudentGroup("java 15-4"));
        studentGroupDao.create(new StudentGroup("java 15-5"));
        LOG.info("Created new Student Group entities");

        List<StudentGroup> listGroup = studentGroupDao.findAll();
        LOG.info("'findAll' operation returned list of all groups. list size  - {}", listGroup.size());

        StudentGroup testGroup = listGroup.get(2);
        Long groupId = testGroup.getGroupId();
        StudentGroup foundGroupById = studentGroupDao.findById(groupId);
        LOG.info("Student Group entity with groupId - {}, groupName - '{}' was found by 'findById' operation (id = {})",
                foundGroupById.getGroupId(), foundGroupById.getGroupName(), groupId);

        testGroup.setGroupName("the best");
        studentGroupDao.update(testGroup);
        LOG.info("Updated existing Student Group entity with new groupName value");

        StudentGroup deleteGroup = listGroup.get(1);
        boolean isGroupDeleted = studentGroupDao.delete(deleteGroup);
        LOG.info("Delete operation returned '{}' value", isGroupDeleted);
        LOG.info("********* Student Group DAO Demo finished *********");


        //Student DAO Demo:
        LOG.info("********* Student DAO Demo started *********");
        StudentDao studentDao = daoFactory.getStudentDao();
        LOG.info("Student DAO was fetched from DAO Factory");

        Student student1 = new Student("Alex", "Ross", new Long(1), Date.valueOf("2015-03-25"), new Long(1), new Long(1));
        Student student2 = new Student("Nick", "Lynn", new Long(4), Date.valueOf("2015-11-11"), new Long(2), new Long(2));
        Student student3 = new Student("Andrew", "Bullock", new Long(3), Date.valueOf("2015-05-04"), new Long(2), new Long(1));
        Student student4 = new Student("Jordan", "Mcmillan", new Long(1), Date.valueOf("2015-02-06"), new Long(2), new Long(2));
        Student student5 = new Student("Marlon", "Coleman", new Long(4), Date.valueOf("2015-07-26"), new Long(2), new Long(1));
        studentDao.create(student1);
        studentDao.create(student2);
        studentDao.create(student3);
        studentDao.create(student4);
        studentDao.create(student5);
        LOG.info("Created new Student entities");

        List<Student> listStudent = studentDao.findAll();
        LOG.info("'findAll' operation returned list of all students. list size  - {}", listStudent.size());

        Student testStudent = listStudent.get(4);
        Long studentId = testStudent.getStudentId();
        Student foundStudentById = studentDao.findById(studentId);
        LOG.info("Student entity with studentId - {}, firstName - '{}', lastName - '{}'was found by 'findById' operation",
                foundStudentById.getStudentId(), foundStudentById.getFirstName(), foundStudentById.getLastName());

        testStudent.setTermId(new Long(2));
        studentDao.update(testStudent);
        LOG.info("Updated existing Student entity with new termId value");

        Student deleteStudent = listStudent.get(2);
        boolean isStudentDeleted = studentDao.delete(deleteStudent);
        LOG.info("Delete operation returned '{}' value", isStudentDeleted);
        LOG.info("********* Student DAO Demo finished *********");


        //Journal DAO Demo:
        LOG.info("********* Journal DAO Demo started ********* ");
        JournalDao journalDao = daoFactory.getJournalDao();
        LOG.info("Journal DAO was fetched from DAO Factory");

        Journal journal1 = new Journal(new Long(1), new Long(3), new Long(3));
        Journal journal2 = new Journal(new Long(1),new Long(1), new Long(2));
        Journal journal3 = new Journal(new Long(4), new Long(3), new Long(1));
        Journal journal4 = new Journal(new Long(4), new Long(5), new Long(3));
        Journal journal5 = new Journal(new Long(4), new Long(1), new Long(6));
        journalDao.create(journal1);
        journalDao.create(journal2);
        journalDao.create(journal3);
        journalDao.create(journal4);
        journalDao.create(journal5);
        LOG.info("Created new Journal entities");

        List<Journal> listJournal = journalDao.findAll();
        LOG.info("'findAll' operation returned list of all journals. list size  - {}", listJournal.size());

        Journal testJournal = listJournal.get(0);
        Long journalId = testJournal.getJournalId();
        Journal foundJournalById = journalDao.findById(journalId);
        LOG.info("Journal entity with journalId - {}, studentId - {}, subjectId - {}, gradeId - {}"+
                        "was found by 'findById' operation (id = {})",
                foundJournalById.getJournalId(),foundJournalById.getStudentId(),
                foundJournalById.getSubjectId(),foundJournalById.getGradeId(),journalId);

        testJournal.setGradeId(new Long(4));
        journalDao.update(testJournal);
        LOG.info("Updated existing Journal entity with new gradeId value - '{}'", testJournal.getGradeId());

        Journal deleteJournal = listJournal.get(1);
        boolean isJournalDeleted = journalDao.delete(deleteJournal);
        LOG.info("Delete operation returned '{}' value",isJournalDeleted);
        LOG.info("********* Journal DAO Demo finished *********");
    }
}
