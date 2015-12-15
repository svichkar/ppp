/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.OrderInWorkDao;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author mixeyes
 *
 */
public class OrderInWorkDaoImpl implements OrderInWorkDao {
	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getAllOrders(
	 * )
	 */
	@Override
	public List<OrderInWork> getAllOrders() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<OrderInWork> orderInWorks = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWorks = session.createCriteria(OrderInWork.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		OrderInWork orderInWork = new OrderInWork();
		Transaction tx = session.beginTransaction();
		try {
			orderInWork = (OrderInWork) session.createCriteria(OrderInWork.class,"order")
					.createAlias("order.car", "car")
					.createAlias("order.orderStatus", "orderStatus")
					.add(Restrictions.eq("car.regNumber", reg_number))
					.add(Restrictions.disjunction().add(Restrictions.eq("orderStatus.orderStatusName", "in work")).add(Restrictions.eq("orderStatus.orderStatusName", "waiting"))).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		OrderInWork orderInWork = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWork = (OrderInWork) session.createCriteria(OrderInWork.class)
					.add(Restrictions.eq("customer.last_name", last_name))
					.add(Restrictions.eq("customer.first_name", first_name))
					.add(Restrictions.eq("order_status.order_status_name", "in work")).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		OrderInWork orderInWork = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWork = (OrderInWork) session.createCriteria(OrderInWork.class)
					.add(Restrictions.eq("orderId", order_id)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
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
		orderInWork.setOrder_status(DaoFactory.getOrderStatusDaoImpl().getStatusByID(1));
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(orderInWork);
			tx.commit();
		}
		catch (Exception ex) {
			tx.rollback();
			logger.error(ex );
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
	public void changeOrderStatusByOrderID(Integer order_id, Integer order_status_id) {
		OrderInWork orderInWork = getOrderByID(order_id);
		orderInWork.setOrder_status(DaoFactory.getOrderStatusDaoImpl().getStatusByID(order_status_id));
		if(order_status_id.equals(2))
			orderInWork.setDatetime_finish(new Date());
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(orderInWork);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
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
	public void updateOrderDescriptionByID(Integer order_id, String description) {
		OrderInWork orderInWork = getOrderByID(order_id);
		orderInWork.setOrder_description(description);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(orderInWork);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {

			session.delete(orderInWork);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
		orderInWork = getOrderByID(order_id);
		if (orderInWork == null)
			return true;
		else
			return false;
	}
}
