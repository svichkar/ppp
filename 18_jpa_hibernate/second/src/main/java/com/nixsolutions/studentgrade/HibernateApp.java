package com.nixsolutions.studentgrade;

import com.nixsolutions.studentgrade.dao.*;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by svichkar on 1/13/2016.
 */
public class HibernateApp {

    private static final Logger LOG = LogManager.getLogger(HibernateApp.class);

    public static void main(String args[]) {

        String login = "admin";
        String pass = "admin";

        DaoFactory daoFactory = new DaoFactory();
        RoleDao roleDao = daoFactory.getRoleDao();
        UserDao userDao = daoFactory.getUserDao();
        TermDao termDao = daoFactory.getTermDao();
        JournalDao journalDao = daoFactory.getJournalDao();
        StudentDao studentDao = daoFactory.getStudentDao();

        List<Role> roles = roleDao.findAll();
        List<User> users = userDao.findAll();


        Student st = studentDao.findById(1L);
        Term t = termDao.findById(2L);

        journalDao.findByStudentAndTerm(1L, 2L);

        Random r = new Random();

        Set<String> a = new HashSet<>();
        for (int i = 0; i < 2000; i++) {

            a.add(String.format("%s, %s", r.nextInt(30) + 1,r.nextInt(5) + 1));

        }


        for (int i = 0; i < 150; i++) {
            //System.out.println(String.format("INSERT INTO journal(student_id, subject_id, grade_id) VALUES(%s, %s);", a.toArray()[i], r.nextInt(5) + 1));
        }

        LOG.info("Demo Hibernate Program finished.");


    }
}
