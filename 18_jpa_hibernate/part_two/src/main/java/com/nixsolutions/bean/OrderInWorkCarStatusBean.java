package com.nixsolutions.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.OrderInWorkCarStatusDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderInWorkCarStatus;

public class OrderInWorkCarStatusBean implements OrderInWorkCarStatusDAO<OrderInWorkCarStatus> {

	private final static Logger LOG = LogManager.getLogger(OrderInWorkCarStatus.class);
	public static SessionFactory sessionFactory;

	public OrderInWorkCarStatusBean() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Deprecated
	@Override
	public void create(OrderInWorkCarStatus t) {
	}

	@Deprecated
	@Override
	public void update(OrderInWorkCarStatus t) {

	}

	@Deprecated
	@Override
	public void delete(OrderInWorkCarStatus t) {

	}

	@Override
	public OrderInWorkCarStatus findByPK(long orderid) {
		OrderInWorkCarStatus orderInWorkCarStatus = null;
		Transaction transaction = null;
		Session session = null;
		OrderInWork orderInWork = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			orderInWork = (OrderInWork) session.createCriteria(OrderInWork.class).add(Restrictions.eq("order_id", orderid))
					.uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}
		if (orderInWork != null) {
			orderInWorkCarStatus = new OrderInWorkCarStatus();
			orderInWorkCarStatus.setCar_id(orderInWork.getCar().getCarId());
			orderInWorkCarStatus.setDatetime_end(orderInWork.getDatetime_end());
			orderInWorkCarStatus.setDatetime_start(orderInWork.getDatetime_start());
			orderInWorkCarStatus.setDescription(orderInWork.getDescription());
			orderInWorkCarStatus.setId(orderInWork.getOrderInWorkId());
			orderInWorkCarStatus.setModel(orderInWork.getCar().getModel());
			orderInWorkCarStatus.setOrder_status_id(orderInWork.getOrder_status().getOrderStatusId());
			orderInWorkCarStatus.setOrder_status_name(orderInWork.getOrder_status().getOrder_status_name());
			orderInWorkCarStatus.setVin(orderInWork.getCar().getVin());
		}
		return orderInWorkCarStatus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWorkCarStatus> getAll() {
		List<OrderInWorkCarStatus> results = new ArrayList<>();
		Transaction transaction = null;
		Session session = null;
		List<OrderInWork> orderInWorkes = new ArrayList<>();
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			orderInWorkes.addAll(session.createCriteria(OrderInWork.class).list());
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}

		for (OrderInWork orderInWork : orderInWorkes) {
			OrderInWorkCarStatus orderInWorkCarStatus = new OrderInWorkCarStatus();
			orderInWorkCarStatus.setCar_id(orderInWork.getCar().getCarId());
			orderInWorkCarStatus.setDatetime_end(orderInWork.getDatetime_end());
			orderInWorkCarStatus.setDatetime_start(orderInWork.getDatetime_start());
			orderInWorkCarStatus.setDescription(orderInWork.getDescription());
			orderInWorkCarStatus.setId(orderInWork.getOrderInWorkId());
			orderInWorkCarStatus.setModel(orderInWork.getCar().getModel());
			orderInWorkCarStatus.setOrder_status_id(orderInWork.getOrder_status().getOrderStatusId());
			orderInWorkCarStatus.setOrder_status_name(orderInWork.getOrder_status().getOrder_status_name());
			orderInWorkCarStatus.setVin(orderInWork.getCar().getVin());
			results.add(orderInWorkCarStatus);
		}

		return results;
	}

}
