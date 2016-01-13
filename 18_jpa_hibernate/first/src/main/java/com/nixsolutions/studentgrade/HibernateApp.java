package com.nixsolutions.studentgrade;

import com.nixsolutions.studentgrade.entity.*;
import com.nixsolutions.studentgrade.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;

/**
 * Created by svichkar on 1/13/2016.
 */
public class HibernateApp {

    private static final Logger LOG = LogManager.getLogger(HibernateApp.class);

    public static void main(String args[]) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        /**
         * ROLE & USER
         */
        Role role1 = new Role();
        role1.setRoleName("admin");
        session.save(role1);
        Role role2 = new Role();
        role2.setRoleName("guest");
        session.save(role2);

        User user1 = new User();
        user1.setFirstName("Kos");
        user1.setLastName("Sv");
        user1.setLogin("kos");
        user1.setUserPassword("123");
        user1.setEmail("email");
        user1.setRole(role1);
        session.save(user1);
        User user2 = new User();
        user2.setFirstName("Kos");
        user2.setLastName("Sv");
        user2.setLogin("qwe");
        user2.setUserPassword("321");
        user2.setEmail("email2");
        user2.setRole(role2);
        session.save(user2);

        /**
         * Grade & Term & Status & StudentGroup
         */
        Grade grade1 = new Grade();
        grade1.setGradeName("good");
        session.save(grade1);
        Grade grade2 = new Grade();
        grade2.setGradeName("not so good");
        session.save(grade2);
        Grade grade3 = new Grade();
        grade3.setGradeName("upper good");
        session.save(grade3);

        Term term1 = new Term();
        term1.setTermName("first");
        session.save(term1);
        Term term2 = new Term();
        term2.setTermName("second");
        session.save(term2);

        Status status1 = new Status();
        status1.setStatusName("status1");
        session.save(status1);
        Status status2 = new Status();
        status2.setStatusName("status2");
        session.save(status2);

        StudentGroup gr1 = new StudentGroup();
        gr1.setGroupName("group1");
        session.save(gr1);
        StudentGroup gr2 = new StudentGroup();
        gr2.setGroupName("group2");
        session.save(gr2);

        /**
         * Subject
         */
        Subject sub1 = new Subject();
        sub1.setSubjectName("sub1");
        sub1.setTerm(term1);
        session.save(sub1);
        Subject sub2 = new Subject();
        sub2.setSubjectName("sub2");
        sub2.setTerm(term1);
        session.save(sub2);
        Subject sub3 = new Subject();
        sub3.setSubjectName("sub3");
        sub3.setTerm(term2);
        session.save(sub3);

        /**
         * Student
         */
        Student stud1 = new Student();
        stud1.setFirstName("name1");
        stud1.setLastName("lastName1");
        stud1.setTerm(term1);
        stud1.setAdmissionDate(Date.valueOf("2015-06-25"));
        stud1.setStatus(status1);
        stud1.setStudentGroup(gr1);
        session.save(stud1);

        Student stud2 = new Student();
        stud2.setFirstName("name2");
        stud2.setLastName("lastName2");
        stud2.setTerm(term1);
        stud2.setAdmissionDate(Date.valueOf("2015-06-01"));
        stud2.setStatus(status1);
        stud2.setStudentGroup(gr2);
        session.save(stud2);

        Student stud3 = new Student();
        stud3.setFirstName("name3");
        stud3.setLastName("lastName3");
        stud3.setTerm(term2);
        stud3.setAdmissionDate(Date.valueOf("2016-01-01"));
        stud3.setStatus(status2);
        stud3.setStudentGroup(gr1);
        session.save(stud3);

        /**
         * Journal
         */
        Journal j1 = new Journal();
        j1.setStudent(stud1);
        j1.setSubject(sub1);
        j1.setGrade(grade1);
        session.save(j1);
        Journal j2 = new Journal();
        j2.setStudent(stud1);
        j2.setSubject(sub1);
        j2.setGrade(grade2);
        session.save(j2);
        Journal j3 = new Journal();
        j3.setStudent(stud2);
        j3.setSubject(sub3);
        j3.setGrade(grade3);
        session.save(j3);

        session.close();

        LOG.info("Demo Hibernate Program finished.");
        System.exit(-1);
    }
}
