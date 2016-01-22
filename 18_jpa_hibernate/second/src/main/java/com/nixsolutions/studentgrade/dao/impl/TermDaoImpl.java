package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Term;
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
public class TermDaoImpl implements TermDao {

    @Override
    public void create(Term term) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(term);
        transaction.commit();
    }

    @Override
    public void update(Term term) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(term);
        transaction.commit();
    }

    @Override
    public void delete(Term term) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.delete(term);
        transaction.commit();
    }

    @Override
    public List<Term> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Term> list = session.createCriteria(Term.class).list();
        transaction.commit();

        return list;
    }

    @Override
    public Term findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Term.class);
        criteria.add(Restrictions.idEq(id));
        List<Term> results = criteria.list();
        transaction.commit();

        if (results.isEmpty() == false) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Term findByName(String termName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Term.class);
        criteria.add(Restrictions.eq("termName", termName));
        List<Term> results = criteria.list();
        transaction.commit();

        if (results.isEmpty() == false) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
