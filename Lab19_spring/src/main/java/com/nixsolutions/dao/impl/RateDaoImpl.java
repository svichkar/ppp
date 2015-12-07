package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.RateDao;
import com.nixsolutions.entity.Rate;
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
public class RateDaoImpl implements RateDao {

    private static Logger LOG = LogManager.getLogger(RateDaoImpl.class.getName());

    public RateDaoImpl() {
    }

    public void create(Rate rate) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(rate);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void update(Rate rate) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(rate);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void delete(Rate rate) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(rate);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public Rate getByRateId(int rateId) {
        Rate rate = new Rate();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            rate = (Rate) session.get(Rate.class, rateId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return rate;
    }

    public Rate getByRateValue(String rateValue) {
        Rate rate = new Rate();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria c = session.createCriteria(Rate.class);
            c.add(Restrictions.eq("rateValue", rateValue));
            List list = c.list();
            rate = (Rate) list.get(0);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return rate;
    }

    public List<Rate> getAll() {
        List<Rate> toReturn = new ArrayList<Rate>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            toReturn.addAll(session.createCriteria(Rate.class).list());
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
