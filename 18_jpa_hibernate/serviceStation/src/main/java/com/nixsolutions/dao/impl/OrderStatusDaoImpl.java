package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.OrderStatusDao;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.util.HibernateUtil;

public class OrderStatusDaoImpl implements OrderStatusDao {

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.OrderStatusDao#createNewStatus(java.lang.String)
	 */
	@Override
	public void createNewStatus(String status) {
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrder_status_name(status);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(orderStatus);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.OrderStatusDao#getAllStatus()
	 */
	@Override
	public List<OrderStatus> getAllStatus() {
		List<OrderStatus> statuses = new ArrayList<OrderStatus>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			statuses = session.createCriteria(OrderStatus.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return statuses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.OrderStatusDao#getStatusByID(java.lang.Integer)
	 */
	@Override
	public OrderStatus getStatusByID(Integer order_status_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		OrderStatus orderStatus = null;
		Transaction tx = session.beginTransaction();
		try {
			orderStatus = (OrderStatus) session.createCriteria(OrderStatus.class)
					.add(Restrictions.eq("orderStatusId", order_status_id)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return orderStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.OrderStatusDao#deleteStatusByName(java.lang.String)
	 */
	@Override
	public boolean deleteStatusByID(Integer order_status_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		OrderStatus orderStatus = getStatusByID(order_status_id);
		Transaction tx = session.beginTransaction();
		try {

			session.delete(orderStatus);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
		orderStatus = getStatusByID(order_status_id);
		if (orderStatus == null)
			return true;
		else
			return false;
	}

}
