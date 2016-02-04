package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.JournalDao;
import com.nixsolutions.studentgrade.model.Journal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by konstantin on 1/29/2016.
 */
@Repository
public class JournalDaoImpl implements JournalDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Journal journal) {

        sessionFactory.getCurrentSession().save(journal);
    }

    @Override
    public void update(Journal journal) {

        sessionFactory.getCurrentSession().saveOrUpdate(journal);
    }

    @Override
    public void delete(Journal journal) {

        sessionFactory.getCurrentSession().delete(journal);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Journal> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<Journal> list = session.createCriteria(Journal.class).list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Journal findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Journal journal = (Journal) session.get(Journal.class, id);
        return journal;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Journal> findByStudentAndTerm(Long studentId, Long termId) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Journal.class);
        criteria.createAlias("student", "student");
        criteria.createAlias("subject.term", "subject_term");
        criteria.add(Restrictions.and(Restrictions.eq("student.studentId", studentId),
                Restrictions.eq("subject_term.termId", termId)));
        List<Journal> journals = criteria.list();
        return journals;
    }
}
