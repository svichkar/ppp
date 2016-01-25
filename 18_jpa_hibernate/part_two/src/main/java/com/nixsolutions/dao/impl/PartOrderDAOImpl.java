package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.PartOrderDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Part;
import com.nixsolutions.entities.PartOrder;

public class PartOrderDAOImpl implements PartOrderDAO<PartOrder> {

	private final static Logger LOG = LogManager.getLogger(PartOrderDAOImpl.class);
	public static SessionFactory sessionFactory;

	public PartOrderDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(PartOrder t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(PartOrder t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(PartOrder t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public PartOrder findByPK(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartOrder> getAll() {
		List<PartOrder> lPO = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lPO.addAll(session.createCriteria(PartOrder.class).list());
		tx.commit();
		return lPO;
	}

	@SuppressWarnings("unchecked")
	public List<PartOrder> getAllForOrder(long order_id) {
		List<PartOrder> lPO = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class)
				.add(Restrictions.eq("order_id", order_id)).uniqueResult();
		lPO.addAll(session.createCriteria(PartOrder.class).add(Restrictions.eq("order", oiw)).list());
		tx.commit();
		return lPO;
	}

	public PartOrder findbyPartAndOrder(long order_id, long part_id) {
		PartOrder po = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class)
				.add(Restrictions.eq("order_id", order_id)).uniqueResult();
		Part part = (Part) session.createCriteria(Part.class)
				.add(Restrictions.eq("part_id", part_id)).uniqueResult();

		po = (PartOrder) session.createCriteria(PartOrder.class).add(Restrictions.eq("order", oiw))
				.add(Restrictions.eq("part", part)).uniqueResult();
		tx.commit();
		return po;
	}

}
