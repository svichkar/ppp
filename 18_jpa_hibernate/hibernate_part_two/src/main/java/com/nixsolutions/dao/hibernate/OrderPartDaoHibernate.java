package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class OrderPartDaoHibernate implements OrderPartDAO {

	public static Logger LOG = LogManager.getLogger(OrderPartDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(OrderPart entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(OrderPart entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(OrderPart entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public OrderPart getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		OrderPart orderPart = null;
		Transaction tx = session.beginTransaction();
		orderPart = (OrderPart) session.get(OrderPart.class, id);
		tx.commit();
		return orderPart;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPart> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<OrderPart> orderPartList = null;
		Transaction tx = session.beginTransaction();
		orderPartList = session.createCriteria(OrderPart.class).list();
		tx.commit();
		return orderPartList;
	}

	@Override
	public OrderPart getByPK(long orderId, long partId) {
		Session session = sessionFactory.getCurrentSession();
		OrderPart orderPart = null;
		Transaction tx = session.beginTransaction();
		orderPart = (OrderPart) session.createCriteria(OrderPart.class)
				.add(Restrictions.eq("order.orderId", orderId))
				.add(Restrictions.eq("part.partId", partId))
				.uniqueResult();
		tx.commit();
		return orderPart;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPart> getByOrderId(long orderId) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderPart> orderPartList = null;
		Transaction tx = session.beginTransaction();
		orderPartList = session.createCriteria(OrderPart.class).add(Restrictions.eq("order.orderId", orderId)).list();
		tx.commit();
		return orderPartList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPart> getOrderPartByPartId(long partId) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderPart> orderPartList = null;
		Transaction tx = session.beginTransaction();
		orderPartList = session.createCriteria(OrderPart.class).add(Restrictions.eq("part.partId", partId)).list();
		tx.commit();
		return orderPartList;
	}

}
