package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.dao.impl.*;

/**
 * Created by svichkar on 12/18/2015.
 */
public class StudentGradeDaoFactory {

    public GradeDao getGradeDao() {
        return new GradeDaoImpl();
    }

    public JournalDao getJournalDao() {
        return new JournalDaoImpl();
    }

    public StatusDao getStatusDao() {
        return new StatusDaoImpl();
    }

    public StudentDao getStudentDao () {
        return new StudentDaoImpl();
    }

    public StudentGroupDao getStudentGroupDao() {
        return new StudentGroupDaoImpl();
    }

    public SubjectDao getSubjectDao() {
        return new SubjectDaoImpl();
    }

    public TermDao getTermDao() {
        return new TermDaoImpl();
    }

    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public RoleDao getRoleDao() {
        return new RoleDaoImpl();
    }

}
