package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.entities.OrderInWork;

@Repository
@Transactional
public class OrderInWorkDAOImpl implements OrderInWorkDAO<OrderInWork> {

	private final static Logger LOG = LogManager.getLogger(OrderInWorkDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;


	@Override
	public void create(OrderInWork t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(OrderInWork t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(OrderInWork t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public OrderInWork findByPK(long id) {
		return (OrderInWork) sessionFactory.getCurrentSession().get(OrderInWork.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getAll() {
		List<OrderInWork> orderInWorks = new ArrayList<>();
		orderInWorks.addAll(sessionFactory.getCurrentSession().createCriteria(OrderInWork.class).list());
		return orderInWorks;
	}

}
