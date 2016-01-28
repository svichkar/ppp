package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.model.Subject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public class SubjectDaoImpl implements SubjectDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Subject subject) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(subject);
        transaction.commit();
    }

    @Override
    public void update(Subject subject) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(subject);
        transaction.commit();
    }

    @Override
    public void delete(Subject subject) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(subject);
        transaction.commit();
    }

    @Override
    public List<Subject> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Subject> list = session.createCriteria(Subject.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public Subject findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Subject subject = (Subject) session.get(Subject.class, id);
        transaction.commit();
        return subject;
    }

    @Override
    public Subject findByName(String subjectName) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.add(Restrictions.eq("subjectName", subjectName)).uniqueResult();
        List<Subject> subjectList = criteria.list();
        transaction.commit();
        return subjectList.isEmpty() ? null : subjectList.get(0);
    }

    @Override
    public Subject findByNameAndTermId(String subjectName, Long termId) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.createAlias("term", "term");
        criteria.add(Restrictions.and(Restrictions.eq("subjectName", subjectName), Restrictions.eq("term.termId", termId)));
        List<Subject> subjectList = criteria.list();
        transaction.commit();
        return subjectList.isEmpty() ? null : subjectList.get(0);
    }

    @Override
    public List<Subject> findByTermId(Long termId) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.createAlias("term", "term");
        criteria.add(Restrictions.eq("term.termId", termId));
        List<Subject> subjectList = criteria.list();
        transaction.commit();
        return subjectList;
    }
}
