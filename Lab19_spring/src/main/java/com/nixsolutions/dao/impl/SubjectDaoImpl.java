package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.entity.Subject;
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
public class SubjectDaoImpl implements SubjectDao {

    private static Logger LOG = LogManager.getLogger(SubjectDaoImpl.class.getName());

	public SubjectDaoImpl() {
	}

    public void create(Subject subject) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(subject);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void update(Subject subject) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(subject);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void delete(Subject subject) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(subject);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public Subject getBySubjectId(int subjectId) {
        Subject subject = new Subject();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            subject = (Subject) session.get(Subject.class, subjectId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return  subject;
    }

    public List<Subject> getAll() {
        List<Subject> toReturn = new ArrayList<Subject>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            toReturn.addAll(session.createCriteria(Subject.class).list());
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return toReturn;
    }

    public List<Subject> getBySubjectName(String name) {
        List<Subject> toReturn = new ArrayList<Subject>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria c = session.createCriteria(Subject.class);
            c.add(Restrictions.eq("name", name));
            toReturn.addAll(c.list());
            //term = (Term) list.get(0);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return toReturn;
    }

    public List<Subject> getSubjectsByTerm(Term term) {
		List<Subject> toReturn = new ArrayList<Subject>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
        Criteria c = session.createCriteria(Subject.class);
        c.add(Restrictions.eq("term", term));
        toReturn.addAll(c.list());
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