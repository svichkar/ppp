package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.entity.Journal;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.Subject;
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
public class JournalDaoImpl implements JournalDao {
    private final static Logger LOG = LogManager.getLogger(JournalDaoImpl.class.getName());

    public JournalDaoImpl() {
    }

    public void create(Journal journal) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(journal);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void update(Journal journal) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(journal);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void delete(Journal journal) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(journal);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public Journal getByJournalById(int journalId) {
        Journal journal = new Journal();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            journal = (Journal) session.get(Journal.class, journalId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return journal;
    }

    public List<Journal> getAll() {
        List<Journal> toReturn = new ArrayList<Journal>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            toReturn.addAll(session.createCriteria(Journal.class).list());
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return toReturn;
    }

    public List<Journal> getJournalByStudent(Student student) {
        List<Journal> toReturn = new ArrayList<Journal>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria c = session.createCriteria(Journal.class);
            c.add(Restrictions.eq("student", student));
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

    public List<Journal> getJournalBySubject(Subject subject) {
        List<Journal> toReturn = new ArrayList<Journal>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria c = session.createCriteria(Journal.class);
            c.add(Restrictions.eq("subject", subject));
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