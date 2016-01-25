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
	public OrderInWorkCarStatus findByPK(long id) {
		OrderInWorkCarStatus orderInWorkCS = new OrderInWorkCarStatus();

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class).add(Restrictions.eq("order_id", id))
				.uniqueResult();
		tx.commit();

		orderInWorkCS.setCar_id(oiw.getCar().getCarId());
		orderInWorkCS.setDatetime_end(oiw.getDatetime_end());
		orderInWorkCS.setDatetime_start(oiw.getDatetime_start());
		orderInWorkCS.setDescription(oiw.getDescription());
		orderInWorkCS.setId(oiw.getOrderInWorkId());
		orderInWorkCS.setModel(oiw.getCar().getModel());
		orderInWorkCS.setOrder_status_id(oiw.getOrder_status().getOrderStatusId());
		orderInWorkCS.setOrder_status_name(oiw.getOrder_status().getOrder_status_name());
		orderInWorkCS.setVin(oiw.getCar().getVin());

		return orderInWorkCS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWorkCarStatus> getAll() {
		List<OrderInWorkCarStatus> listResult = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<OrderInWork> lOIW = session.createCriteria(OrderInWork.class).list();
		tx.commit();

		for (OrderInWork oiw : lOIW) {
			OrderInWorkCarStatus orderInWorkCS = new OrderInWorkCarStatus();
			orderInWorkCS.setCar_id(oiw.getCar().getCarId());
			orderInWorkCS.setDatetime_end(oiw.getDatetime_end());
			orderInWorkCS.setDatetime_start(oiw.getDatetime_start());
			orderInWorkCS.setDescription(oiw.getDescription());
			orderInWorkCS.setId(oiw.getOrderInWorkId());
			orderInWorkCS.setModel(oiw.getCar().getModel());
			orderInWorkCS.setOrder_status_id(oiw.getOrder_status().getOrderStatusId());
			orderInWorkCS.setOrder_status_name(oiw.getOrder_status().getOrder_status_name());
			orderInWorkCS.setVin(oiw.getCar().getVin());
			listResult.add(orderInWorkCS);
		}

		return listResult;
	}

}
