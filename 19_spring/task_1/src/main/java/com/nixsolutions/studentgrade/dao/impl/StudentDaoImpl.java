package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.model.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by svichkar on 1/29/2016.
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Student student) {

        sessionFactory.getCurrentSession().save(student);
    }

    @Override
    public void update(Student student) {

       sessionFactory.getCurrentSession().saveOrUpdate(student);
    }

    @Override
    public void delete(Student student) {

        sessionFactory.getCurrentSession().delete(student);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<Student> list = session.createCriteria(Student.class).list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Student findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Student student = (Student) session.get(Student.class, id);
        return student;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Student findByNameAndLastName(String name, String lastName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.and(Restrictions.eq("firstName", name), Restrictions.eq("lastName", lastName)));
        List<Student> students = criteria.list();
        return students.isEmpty() ? null : students.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findByLastName(String lastName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("lastName", lastName).ignoreCase());
        List<Student> students = criteria.list();
        return students.isEmpty() ? null : students;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findByGroupId(Long groupId) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.createAlias("studentGroup", "studentGroup");
        criteria.add(Restrictions.eq("studentGroup.groupId", groupId));
        List<Student> students = criteria.list();
        return students.isEmpty() ? null : students;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findByLastNameAndGroupId(String lastName, Long groupId) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.createAlias("studentGroup", "studentGroup");
        criteria.add(Restrictions.and(Restrictions.eq("studentGroup.groupId", groupId), Restrictions.eq("lastName", lastName).ignoreCase()));
        List<Student> students = criteria.list();
        return students.isEmpty() ? null : students;
    }
}
