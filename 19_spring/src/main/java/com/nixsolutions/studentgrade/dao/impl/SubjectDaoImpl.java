package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.model.Subject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@Repository
public class SubjectDaoImpl implements SubjectDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Subject subject) {

        sessionFactory.getCurrentSession().save(subject);
    }

    @Override
    public void update(Subject subject) {

        sessionFactory.getCurrentSession().saveOrUpdate(subject);
    }

    @Override
    public void delete(Subject subject) {

        sessionFactory.getCurrentSession().delete(subject);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Subject> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<Subject> list = session.createCriteria(Subject.class).list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Subject findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Subject subject = (Subject) session.get(Subject.class, id);
        return subject;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Subject findByName(String subjectName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.add(Restrictions.eq("subjectName", subjectName).ignoreCase());
        Subject subject = (Subject) criteria.uniqueResult();
        return subject;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Subject findByNameAndTermId(String subjectName, Long termId) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.createAlias("term", "term");
        criteria.add(Restrictions.and(Restrictions.eq("subjectName", subjectName).ignoreCase(),
                Restrictions.eq("term.termId", termId)));
        Subject subject = (Subject) criteria.uniqueResult();
        return subject;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Subject> findByTermId(Long termId) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.createAlias("term", "term");
        criteria.add(Restrictions.eq("term.termId", termId));
        List<Subject> subjectList = criteria.list();
        return subjectList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Subject> findByTermName(String termName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.createAlias("term", "term");
        criteria.add(Restrictions.eq("term.termName", termName));
        List<Subject> subjectList = criteria.list();
        return subjectList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Subject findByNameAndTerm(String subjectName, String termName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Subject.class);
        criteria.createAlias("term", "term");
        criteria.add(Restrictions.and(Restrictions.eq("subjectName", subjectName).ignoreCase(),
                Restrictions.eq("term.termName", termName)));
        Subject subject = (Subject) criteria.uniqueResult();
        return subject;
    }
}
