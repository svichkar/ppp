package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class OrderPartDAOImpl implements OrderPartDAO {

	public static Logger LOG = LogManager.getLogger(OrderPartDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(OrderPart entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("orderPart", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void update(OrderPart entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("orderPart", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(OrderPart entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("orderPart", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public OrderPart getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		OrderPart orderPart = null;
		Transaction tx = session.beginTransaction();
		try {
			orderPart = (OrderPart) session.byId(OrderPart.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderPart;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPart> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<OrderPart> orderPartList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderPartList = session.createCriteria(OrderPart.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderPartList;
	}

	@Override
	public OrderPart getByPK(long orderId, long partId) {
		Session session = sessionFactory.getCurrentSession();
		OrderPart orderPart = null;
		Transaction tx = session.beginTransaction();
		try {
			orderPart = (OrderPart) session.createCriteria(OrderPart.class).add(Restrictions.eq("order.orderId", orderId))
					.add(Restrictions.eq("part.partId", partId)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderPart;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPart> getByOrderId(long orderId) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderPart> orderPartList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderPartList = session.createCriteria(OrderPart.class).add(Restrictions.eq("order.orderId", orderId)).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderPartList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPart> getOrderPartByPart(Part part) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderPart> orderPartList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderPartList = session.createCriteria(OrderPart.class).add(Restrictions.eq("part.partId", part.getPartId())).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderPartList;
	}

}
