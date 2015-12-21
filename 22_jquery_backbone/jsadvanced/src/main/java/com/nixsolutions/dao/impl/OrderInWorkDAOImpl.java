package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.hibernate.entity.OrderInWork;

@Repository("orderInWorkDAO")
@Transactional
public class OrderInWorkDAOImpl implements OrderInWorkDAO {

	public static Logger LOG = LogManager.getLogger(OrderInWorkDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(OrderInWork entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("orderInWork", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(OrderInWork entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("orderInWork", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(OrderInWork entity) {
		try {
			sessionFactory.getCurrentSession().delete("orderInWork", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public OrderInWork getByPK(long id) {
		try {
			return (OrderInWork) sessionFactory.getCurrentSession().byId(OrderInWork.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderInWork.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByUserId(int userId) {
		try {
		return sessionFactory.getCurrentSession().createCriteria(OrderInWork.class, "order")
				.createAlias("order.car", "car")
				.createAlias("car.customer", "customer")
				.createAlias("customer.user", "user")
				.add(Restrictions.eq("user.userId", userId))
				.list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByCarId(int carId) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderInWork.class)
					.add(Restrictions.eq("car.carId", carId))
					.list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByCustomerId(int customerId) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderInWork.class, "order")
					.createAlias("order.car", "car")
					.createAlias("car.customer", "customer")
					.add(Restrictions.eq("customer.customerId", customerId))
					.list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@Override
	public OrderInWork getByPK(int id) {
		return getByPK((long) id);
	}

}
