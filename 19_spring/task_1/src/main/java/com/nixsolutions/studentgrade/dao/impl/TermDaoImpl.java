package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.model.Term;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by svichkar on 1/27/2016.
 */
@Repository
public class TermDaoImpl implements TermDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Term term) {

        sessionFactory.getCurrentSession().save(term);
    }

    @Override
    public void update(Term term) {

        sessionFactory.getCurrentSession().saveOrUpdate(term);
    }

    @Override
    public void delete(Term term) {

        sessionFactory.getCurrentSession().delete(term);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Term> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<Term> list = session.createCriteria(Term.class).list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Term findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Term term = (Term) session.get(Term.class, id);
        return term;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Term findByName(String termName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Term.class);
        criteria.add(Restrictions.eq("termName", termName));
        Term term = (Term) criteria.uniqueResult();
        return term;
    }
}
