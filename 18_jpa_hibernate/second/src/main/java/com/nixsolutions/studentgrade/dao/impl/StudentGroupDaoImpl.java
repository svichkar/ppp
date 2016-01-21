package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentGroupDao;
import com.nixsolutions.studentgrade.entity.StudentGroup;
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
public class StudentGroupDaoImpl implements StudentGroupDao {

    @Override
    public void create(StudentGroup group) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(group);
        transaction.commit();

        session.close();
    }

    @Override
    public void update(StudentGroup group) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(group);
        transaction.commit();

        session.close();
    }

    @Override
    public void delete(StudentGroup group) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.delete(group);
        transaction.commit();

        session.close();
    }

    @Override
    public List<StudentGroup> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<StudentGroup> list = session.createCriteria(StudentGroup.class).list();
        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public StudentGroup findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(StudentGroup.class);
        criteria.add(Restrictions.idEq(id));
        List<StudentGroup> results = criteria.list();
        transaction.commit();
        session.close();

        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }

    }

    @Override
    public StudentGroup findByName(String groupName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(StudentGroup.class);
        criteria.add(Restrictions.eq("groupName", groupName));
        List<StudentGroup> results = criteria.list();
        transaction.commit();
        session.close();

        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
