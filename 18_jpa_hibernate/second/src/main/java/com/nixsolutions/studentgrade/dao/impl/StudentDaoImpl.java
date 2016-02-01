package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.exception.CustomDaoException;
import com.nixsolutions.studentgrade.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class StudentDaoImpl implements StudentDao {

    @Override
    public void create(Student student) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public void update(Student student) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(student);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public void delete(Student student) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(student);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public List<Student> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Student> list;
        try {
            list = session.createCriteria(Student.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return list;
    }

    @Override
    public Student findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student result;
        try {
            result = (Student) session.get(Student.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return result;
    }

    @Override
    public Student findByNameAndLastName(String firsName, String lastName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.and(Restrictions.eq("firstName", firsName).ignoreCase(),
                Restrictions.eq("lastName", lastName).ignoreCase()));
        Student student;
        try {
            student = (Student) criteria.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return student;
    }

    @Override
    public List<Student> findByLastName(String lastName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("lastName", lastName).ignoreCase());
        List<Student> results;
        try {
            results = criteria.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return results;
    }

    @Override
    public List<Student> findByGroupId(Long groupId) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.createAlias("studentGroup", "group");
        criteria.add(Restrictions.eq("group.groupId", groupId));
        List<Student> results;
        try {
            results = criteria.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return results;
    }

    @Override
    public List<Student> findByLastNameAndGroupId(String lastName, Long groupId) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        criteria.createAlias("studentGroup", "group");
        criteria.add(Restrictions.and(Restrictions.eq("lastName", lastName).ignoreCase(),
                Restrictions.eq("group.groupId", groupId)));
        List<Student> results;
        try {
            results = criteria.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return results;
    }
}
