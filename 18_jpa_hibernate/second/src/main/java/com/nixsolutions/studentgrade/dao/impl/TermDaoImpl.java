package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.exception.CustomDaoException;
import com.nixsolutions.studentgrade.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class TermDaoImpl implements TermDao {

    public void create(Term term) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.saveOrUpdate(term);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    public void update(Term term) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(term);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    public void delete(Term term) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(term);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    public List<Term> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Term> list;
        try {
            list = session.createCriteria(Term.class).list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    public Term findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Term term;

        try {
            term = (Term) session.load(Term.class, id);
            transaction.commit();
            return term;
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    public Term findByName(String termName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Term.class);
        criteria.add(Restrictions.eq("termName", termName)).uniqueResult();
        Term term;
        try {
            term = (Term) criteria.list();
            transaction.commit();
            return term;
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }
}

