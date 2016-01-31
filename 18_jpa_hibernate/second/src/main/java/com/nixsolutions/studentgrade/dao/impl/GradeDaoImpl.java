package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.entity.Grade;
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
public class GradeDaoImpl implements GradeDao {

    @Override
    public void create(Grade grade) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(grade);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public void update(Grade grade) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(grade);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public void delete(Grade grade) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(grade);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public List<Grade> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Grade> list;
        try {
            list = session.createCriteria(Grade.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return list;
    }

    @Override
    public Grade findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Grade grade = null;
        try {
            grade = (Grade) session.get(Grade.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return grade;
    }

    @Override
    public Grade findByName(String gradeName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Grade.class);
        criteria.add(Restrictions.eq("gradeName", gradeName)).uniqueResult();
        List<Grade> results;
        try {
            results = criteria.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }

        return results.isEmpty() ? null : results.get(0);
    }
}
