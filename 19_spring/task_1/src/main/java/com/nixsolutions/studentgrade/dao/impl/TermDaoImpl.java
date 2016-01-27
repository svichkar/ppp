package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.model.Term;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by svichkar on 1/27/2016.
 */

@Repository
@Transactional
public class TermDaoImpl implements TermDao {

    SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Term term) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(term);
        transaction.commit();
    }

    public void update(Term term) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(term);
        transaction.commit();
    }

    public void delete(Term term) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(term);
        transaction.commit();
    }

    public List<Term> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Term> list = session.createCriteria(Term.class).list();
        transaction.commit();
        return list;
    }

    public Term findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Term term = (Term) session.get(Term.class, id);
        transaction.commit();
        return term;
    }

    public Term findByName(String termName) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Term.class);
        criteria.add(Restrictions.eq("termName", termName)).uniqueResult();
        List<Term> terms = criteria.list();
        transaction.commit();
        return terms.isEmpty() ? null : terms.get(0);
    }
}
