package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.JournalDao;
import com.nixsolutions.studentgrade.model.Journal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by konstantin on 1/29/2016.
 */
public class JournalDaoImpl implements JournalDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Journal journal) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(journal);
        transaction.commit();
    }

    @Override
    public void update(Journal journal) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(journal);
        transaction.commit();
    }

    @Override
    public void delete(Journal journal) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(journal);
        transaction.commit();
    }

    @Override
    public List<Journal> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Journal> list = session.createCriteria(Journal.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public Journal findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Journal grade = (Journal) session.get(Journal.class, id);
        transaction.commit();
        return grade;
    }

    @Override
    public List<Journal> findByStudentAndTerm(Long studentId, Long termId) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Journal.class);
        criteria.createAlias("term", "term");
        criteria.createAlias("student", "student");
        criteria.add(Restrictions.and(Restrictions.eq("student.studentId", studentId),
                Restrictions.eq("term.termId", termId)));
        List<Journal> journals = criteria.list();
        transaction.commit();
        return journals.isEmpty() ? null : journals;
    }
}
