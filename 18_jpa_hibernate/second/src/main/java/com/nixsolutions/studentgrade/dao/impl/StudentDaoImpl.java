package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.entity.Student;
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

        session.saveOrUpdate(student);
        transaction.commit();
    }

    @Override
    public void update(Student student) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);
        transaction.commit();
    }

    @Override
    public void delete(Student student) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.delete(student);
        transaction.commit();
    }

    @Override
    public List<Student> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Student> list = session.createCriteria(Student.class).list();
        transaction.commit();

        return list;
    }

    @Override
    public Student findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.idEq(id));
        List<Student> results = criteria.list();
        transaction.commit();

        if (results.isEmpty() == false) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Student findByNameAndLastName(String firsName, String lastName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.and(Restrictions.eq("firstName", firsName), Restrictions.eq("lastName", lastName)));
        List<Student> results = criteria.list();
        transaction.commit();

        if (results.isEmpty() == false) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Student> findByLastName(String lastName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("lastName", lastName).ignoreCase());
        List<Student> results = criteria.list();
        transaction.commit();

        return results;
    }

    @Override
    public List<Student> findByGroupId(Long groupId) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("groupId", groupId));
        List<Student> results = criteria.list();
        transaction.commit();

        return results;
    }

    @Override
    public List<Student> findByLastNameAndGroupId(String lastName, Long groupId) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.and(Restrictions.eq("lastName", lastName).ignoreCase(),
                Restrictions.eq("groupId", groupId)));

        List<Student> results = criteria.list();
        transaction.commit();

        return results;
    }
}
