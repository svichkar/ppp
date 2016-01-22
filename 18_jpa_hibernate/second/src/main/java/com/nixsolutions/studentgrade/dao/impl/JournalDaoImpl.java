package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.JournalDao;
import com.nixsolutions.studentgrade.entity.Journal;
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

        session.saveOrUpdate(journal);
        transaction.commit();


    }

    @Override
    public void update(Journal journal) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(journal);
        transaction.commit();
    }

    @Override
    public void delete(Journal journal) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.delete(journal);
        transaction.commit();
    }

    @Override
    public List<Journal> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Journal> list = session.createCriteria(Journal.class).list();
        transaction.commit();

        return list;
    }

    @Override
    public Journal findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Journal.class);
        criteria.add(Restrictions.idEq(id));
        List<Journal> results = criteria.list();
        transaction.commit();


        if (results.isEmpty() == false) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Journal> findByStudentAndTerm(Long studentId, Long termId) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Journal.class);
        criteria.add(Restrictions.and(Restrictions.eq("studentId", studentId), Restrictions.eq("termId", termId)));
        List<Journal> results = criteria.list();
        transaction.commit();

        return results;
    }
}
