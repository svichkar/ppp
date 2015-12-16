package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.RateDao;
import com.nixsolutions.entity.Rate;
import com.nixsolutions.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class RateDaoImpl implements RateDao {

	public RateDaoImpl() {
	}

	public void create(Rate rate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(rate);
		transaction.commit();
	}

	public void update(Rate rate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(rate);
		transaction.commit();
	}

	public void delete(Rate rate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(rate);
		transaction.commit();
	}

	public Rate getByRateId(int rateId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Rate rate = (Rate) session.get(Rate.class, rateId);
		transaction.commit();
		return rate;
	}

	public Rate getByRateValue(String rateValue) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Rate.class).add(Restrictions.eq("rateValue", rateValue));
		Rate rate = (Rate) c.uniqueResult();
		transaction.commit();
		return rate;
	}

	@SuppressWarnings("unchecked")
	public List<Rate> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Rate> toReturn = session.createCriteria(Rate.class).list();
		transaction.commit();
		return toReturn;
	}
}
