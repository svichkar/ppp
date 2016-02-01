package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.entities.OrderStatus;
import com.nixsolutions.error.CustomDataException;

@Repository
@Transactional
public class OrderStatusDAOImpl implements OrderStatusDAO<OrderStatus> {

	private final static Logger LOG = LogManager.getLogger(OrderStatusDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public void create(OrderStatus t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(OrderStatus t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(OrderStatus t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public OrderStatus findByPK(long id) {
		return (OrderStatus) sessionFactory.getCurrentSession().get(OrderStatus.class, id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderStatus> getAll() {
		List<OrderStatus> ordersStatuses = new ArrayList<>();
		ordersStatuses.addAll(sessionFactory.getCurrentSession().createCriteria(OrderStatus.class).list());
		return ordersStatuses;
	}

}
