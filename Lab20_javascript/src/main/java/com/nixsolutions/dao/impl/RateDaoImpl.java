package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.RateDao;
import com.nixsolutions.entity.Rate;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RateDaoImpl implements RateDao {

	@Autowired
	private SessionFactory sessionFactory;

	public RateDaoImpl() {
	}

	public void create(Rate rate) {
		sessionFactory.getCurrentSession().save(rate);
	}

	public void update(Rate rate) {
		sessionFactory.getCurrentSession().saveOrUpdate(rate);
	}

	public void delete(Rate rate) {
		sessionFactory.getCurrentSession().delete(rate);
	}

	public Rate getByRateId(int rateId) {
		return (Rate) sessionFactory.getCurrentSession().get(Rate.class, rateId);
	}

	public Rate getByRateValue(String rateValue) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Rate.class)
				.add(Restrictions.eq("rateValue", rateValue));
		return (Rate) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Rate> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Rate.class).list();
	}
}
