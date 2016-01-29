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
import com.nixsolutions.error.CustomDataException;

public class PartOrderDAOImpl implements PartOrderDAO<PartOrder> {

	private final static Logger LOG = LogManager.getLogger(PartOrderDAOImpl.class);
	public static SessionFactory sessionFactory;

	public PartOrderDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(PartOrder t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void update(PartOrder t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void delete(PartOrder t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Deprecated
	@Override
	public PartOrder findByPK(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartOrder> getAll() {
		List<PartOrder> partOrders = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			partOrders.addAll(session.createCriteria(PartOrder.class).list());
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return partOrders;
	}

	@SuppressWarnings("unchecked")
	public List<PartOrder> getAllForOrder(long order_id) {
		List<PartOrder> partOrders = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class)
					.add(Restrictions.eq("order_id", order_id)).uniqueResult();
			partOrders.addAll(session.createCriteria(PartOrder.class).add(Restrictions.eq("order", oiw)).list());
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return partOrders;
	}

	public PartOrder findbyPartAndOrder(long order_id, long part_id) {
		PartOrder partOrder = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();

			OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class)
					.add(Restrictions.eq("order_id", order_id)).uniqueResult();
			Part part = (Part) session.createCriteria(Part.class).add(Restrictions.eq("part_id", part_id))
					.uniqueResult();

			partOrder = (PartOrder) session.createCriteria(PartOrder.class).add(Restrictions.eq("order", oiw))
					.add(Restrictions.eq("part", part)).uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return partOrder;
	}

}
