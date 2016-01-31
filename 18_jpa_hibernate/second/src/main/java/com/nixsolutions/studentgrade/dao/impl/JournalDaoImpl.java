package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.JournalDao;
import com.nixsolutions.studentgrade.entity.Journal;
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
public class JournalDaoImpl implements JournalDao {

    @Override
    public void create(Journal journal) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.saveOrUpdate(journal);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public void update(Journal journal) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(journal);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public void delete(Journal journal) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(journal);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public List<Journal> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Journal> list;
        try {
            list = session.createCriteria(Journal.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return list;
    }

    @Override
    public Journal findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Journal journal;
        try {
            journal = (Journal) session.get(Journal.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return journal;
    }

    @Override
    public List<Journal> findByStudentAndTerm(Long studentId, Long termId) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Journal.class);
        criteria.createAlias("student", "student");
        criteria.createAlias("subject", "subject");
        criteria.createAlias("subject.term", "term");
        criteria.add(Restrictions.and(Restrictions.eq("student.studentId", studentId),
                Restrictions.eq("term.termId", termId)));

        List<Journal> results;
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
