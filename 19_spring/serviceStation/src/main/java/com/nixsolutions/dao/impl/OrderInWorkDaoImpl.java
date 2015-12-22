/**
 * 
 */
package com.nixsolutions.dao.impl;

//import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.OrderInWorkDao;
import com.nixsolutions.entity.OrderInWork;

/**
 * @author mixeyes
 *
 */
@Repository("orderInWorkDao")
@Transactional
public class OrderInWorkDaoImpl implements OrderInWorkDao {
	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getAllOrders(
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getAllOrders() {
		List<OrderInWork> orderInWorks = null;
		try {
			orderInWorks = sessionFactory.getCurrentSession().createCriteria(OrderInWork.class).list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return orderInWorks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getOrderByCar
	 * ()
	 */
	@Override
	public OrderInWork getOrderInWorkByCar(String reg_number) {
		OrderInWork orderInWork = new OrderInWork();
		try {
			orderInWork = (OrderInWork) sessionFactory.getCurrentSession().createCriteria(OrderInWork.class, "order")
					.createAlias("order.car", "car").createAlias("order.orderStatus", "orderStatus")
					.add(Restrictions.eq("car.regNumber", reg_number))
					.add(Restrictions.disjunction().add(Restrictions.eq("orderStatus.orderStatusName", "in work"))
							.add(Restrictions.eq("orderStatus.orderStatusName", "waiting")))
					.uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return orderInWork;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#
	 * getOrderByCustomer()
	 */
	@Override
	public OrderInWork getOrderInWorkByCustomer(String last_name, String first_name) {
		OrderInWork orderInWork = null;
		try {
			orderInWork = (OrderInWork) sessionFactory.getCurrentSession().createCriteria(OrderInWork.class)
					.add(Restrictions.eq("customer.last_name", last_name))
					.add(Restrictions.eq("customer.first_name", first_name))
					.add(Restrictions.eq("order_status.order_status_name", "in work")).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return orderInWork;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getOrderByID(
	 * )
	 */
	@Override
	public OrderInWork getOrderByID(Integer order_id) {
		OrderInWork orderInWork = null;
		try {
			orderInWork = (OrderInWork) sessionFactory.getCurrentSession().createCriteria(OrderInWork.class)
					.add(Restrictions.eq("orderId", order_id)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return orderInWork;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.OrderInWorkDao#createNewOrder(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public void createNewOrder(OrderInWork orderInWork) {
		try {
			sessionFactory.getCurrentSession().save(orderInWork);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.OrderInWorkDao#changeOrderStatusByOrderID(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public void updateOrder(OrderInWork orderInWork) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(orderInWork);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.OrderInWorkDao#changeOrderStatusByOrderID(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public boolean deleteOrderByID(Integer order_id) {
		OrderInWork orderInWork = getOrderByID(order_id);
		try {
			sessionFactory.getCurrentSession().delete(orderInWork);
		} catch (Exception ex) {
			logger.error(ex);
		}
		orderInWork = getOrderByID(order_id);
		if (orderInWork == null)
			return true;
		else
			return false;
	}
}
