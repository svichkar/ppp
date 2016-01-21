package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.entity.Grade;
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
public class GradeDaoImpl implements GradeDao {

    @Override
    public void create(Grade grade) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(grade);
        transaction.commit();

        session.close();
    }

    @Override
    public void update(Grade grade) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(grade);
        transaction.commit();

        session.close();
    }

    @Override
    public void delete(Grade grade) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.delete(grade);
        transaction.commit();

        session.close();
    }

    @Override
    public List<Grade> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Grade> list = session.createCriteria(Grade.class).list();
        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public Grade findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Grade.class);
        criteria.add(Restrictions.idEq(id));
        List<Grade> results = criteria.list();
        transaction.commit();
        session.close();

        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Grade findByName(String gradeName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Grade.class);
        criteria.add(Restrictions.eq("gradeName", gradeName));
        List<Grade> results = criteria.list();
        transaction.commit();
        session.close();

        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
