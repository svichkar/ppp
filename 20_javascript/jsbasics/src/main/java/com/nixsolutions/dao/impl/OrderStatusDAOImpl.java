package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.hibernate.entity.OrderStatus;

@Repository("orderStatusDAO")
@Transactional
public class OrderStatusDAOImpl implements OrderStatusDAO {

	public static Logger LOG = LogManager.getLogger(OrderStatusDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(OrderStatus entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("orderStatus", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(OrderStatus entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("orderStatus", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(OrderStatus entity) {
		try {
			sessionFactory.getCurrentSession().delete("orderStatus", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public OrderStatus getByPK(int id) {
		try {
			return (OrderStatus) sessionFactory.getCurrentSession().byId(OrderStatus.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderStatus> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderStatus.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}
}
