package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.entities.OrderStatus;

@Repository
@Transactional
public class OrderStatusDAOImpl implements OrderStatusDAO {

	@Autowired
	protected SessionFactory sessionFactory;

	public void create(OrderStatus t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void update(OrderStatus t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void delete(OrderStatus t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	public OrderStatus findByPK(long id) {
		return (OrderStatus) sessionFactory.getCurrentSession().get(OrderStatus.class, id);

	}

	@SuppressWarnings("unchecked")
	public List<OrderStatus> getAll() {
		List<OrderStatus> ordersStatuses = new ArrayList<>();
		ordersStatuses.addAll(sessionFactory.getCurrentSession().createCriteria(OrderStatus.class).list());
		return ordersStatuses;
	}

}
