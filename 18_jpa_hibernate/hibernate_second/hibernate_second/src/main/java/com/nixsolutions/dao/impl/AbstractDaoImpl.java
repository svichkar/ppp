package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.util.HibernateUtil;

import java.lang.reflect.Field;
public abstract class AbstractDaoImpl {
    private final static Logger LOG = LogManager.getLogger(AbstractDaoImpl.class.getName());

    protected <T> Boolean insert(T obj) {
	Session session = null;
	Transaction transaction = null;
	try {
	    session = HibernateUtil.getSessionFactory().getCurrentSession();
	    transaction = session.beginTransaction();
	    session.save(obj);
	    transaction.commit();
	    return true;
	} catch (Exception e) {
	    if (transaction != null) {
		transaction.rollback();
	    }
	    LOG.error(e);
	    return false;
	}
    }

    protected <T> Boolean delete(T obj) {
	Session session = null;
	Transaction transaction = null;
	try {
	    session = HibernateUtil.getSessionFactory().getCurrentSession();
	    transaction = session.beginTransaction();
	    session.delete(obj);
	    transaction.commit();
	    return true;
	} catch (Exception e) {
	    if (transaction != null) {
		transaction.rollback();
	    }
	    LOG.error(e);
	    return false;
	}
    }

    protected <T> List findBySeveralFields(T obj, String[] fields) {
	List<T> toReturn = new ArrayList<T>();
	Session session = null;
	Transaction transaction = null;
	try {
	    session = HibernateUtil.getSessionFactory().getCurrentSession();
	    transaction = session.beginTransaction();
	    Criteria c = session.createCriteria(obj.getClass());
	    if (fields != null) {
		for (String f : fields) {
		    Field fld = obj.getClass().getDeclaredField(f);
		    fld.setAccessible(true);
		    c.add(Restrictions.eq(f, fld.get(obj)));
		}
	    }
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

    protected <T> Boolean update(T obj) {
	Session session = null;
	Transaction transaction = null;
	try {
	    session = HibernateUtil.getSessionFactory().getCurrentSession();
	    transaction = session.beginTransaction();
	    session.saveOrUpdate(obj);
	    transaction.commit();
	    return true;
	} catch (Exception ex) {
	    if (transaction != null) {
		transaction.rollback();
	    }
	    LOG.error(ex);
	    return false;
	}
    }
}
