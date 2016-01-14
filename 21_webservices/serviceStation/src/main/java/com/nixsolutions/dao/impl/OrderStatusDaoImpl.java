package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.OrderStatusDao;
import com.nixsolutions.entity.OrderStatus;

@Repository("orderStatusDao")
@Transactional
public class OrderStatusDaoImpl implements OrderStatusDao {

	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.OrderStatusDao#createNewStatus(java.lang.String)
	 */
	@Override
	public void createNewStatus(String status) {
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrderStatusName(status);
		try {
			sessionFactory.getCurrentSession().save(orderStatus);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.OrderStatusDao#getAllStatus()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderStatus> getAllStatus() {
		List<OrderStatus> statuses = new ArrayList<OrderStatus>();
		try {
			statuses = sessionFactory.getCurrentSession().createCriteria(OrderStatus.class).list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return statuses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.OrderStatusDao#getStatusByID(java.lang.Integer)
	 */
	@Override
	public OrderStatus getStatusByID(Integer orderStatusId) {
		OrderStatus orderStatus = null;
		try {
			orderStatus = (OrderStatus) sessionFactory.getCurrentSession().createCriteria(OrderStatus.class)
					.add(Restrictions.eq("order_status_id", orderStatusId)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
	public boolean deleteStatusByID(Integer orderStatusId) {
		OrderStatus orderStatus = getStatusByID(orderStatusId);
		try {
			sessionFactory.getCurrentSession().delete(orderStatus);
		} catch (Exception ex) {
			logger.error(ex);
		}
		orderStatus = getStatusByID(orderStatusId);
		return orderStatus == null;
	}

}
