package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.entity.Subject;
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
public class SubjectDaoImpl implements SubjectDao {

    @Override
    public void create(Subject subject) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(subject);
        transaction.commit();
    }

    @Override
    public void update(Subject subject) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(subject);
        transaction.commit();
    }

    @Override
    public void delete(Subject subject) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(subject);
        transaction.commit();
    }

    @Override
    public List<Subject> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Subject> list = session.createCriteria(Subject.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public Subject findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Subject result = (Subject) session.get(Subject.class, id);
        transaction.commit();
        return result;
    }

    @Override
    public Subject findByName(String subjectName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.add(Restrictions.eq("subjectName", subjectName).ignoreCase());
        List<Subject> results = criteria.list();
        transaction.commit();
        if (results.isEmpty() == false) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Subject findByNameAndTermId(String subjectName, Long termId) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.createAlias("term", "term");
        criteria.add(Restrictions.and(Restrictions.eq("subjectName", subjectName).ignoreCase(),
                Restrictions.eq("term.termId", termId)));
        List<Subject> results = criteria.list();
        transaction.commit();

        if (results.isEmpty() == false) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Subject> findByTermId(Long termId) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.createAlias("term", "term");
        criteria.add(Restrictions.eq("term.termId", termId));
        List<Subject> results = criteria.list();
        transaction.commit();
        return results;
    }
}
