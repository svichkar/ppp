package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Term;
import com.nixsolutions.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TermDaoImpl implements TermDao {
    private static Logger LOG = LogManager.getLogger(TermDaoImpl.class.getName());

	public TermDaoImpl() {
	}

    public void create(Term term) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(term);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void update(Term term) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(term);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void delete(Term term) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(term);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public Term getByTermId(int termId) {
        Term term = new Term();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            term = (Term) session.get(Term.class, termId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return  term;
    }

    public Term getByTermAlias(String termName) {
        Term term = new Term();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria c = session.createCriteria(Term.class);
            c.add(Restrictions.eq("alias", termName));
            List list = c.list();
            term = (Term) list.get(0);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return term;
    }

    public List<Term> getAll() {
        List<Term> toReturn = new ArrayList<Term>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            toReturn.addAll(session.createCriteria(Term.class).list());
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return toReturn;
    }
}
