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
import com.nixsolutions.dao.AllPartsInOrderDAO;
import com.nixsolutions.entities.AllPartsInOrder;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Part;
import com.nixsolutions.entities.PartOrder;

public class AllPartsInOrderBean implements AllPartsInOrderDAO<AllPartsInOrder> {

	private final static Logger LOG = LogManager.getLogger(AllPartsInOrderBean.class);
	public static SessionFactory sessionFactory;

	public AllPartsInOrderBean() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Deprecated
	@Override
	public void create(AllPartsInOrder t) {

	}

	@Deprecated
	@Override
	public void update(AllPartsInOrder t) {

	}

	@Deprecated
	@Override
	public void delete(AllPartsInOrder t) {

	}

	@Deprecated
	@Override
	public AllPartsInOrder findByPK(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllPartsInOrder> getAll() {
		List<AllPartsInOrder> results = new ArrayList<>();
		List<PartOrder> partOrders = new ArrayList<>();
		Transaction tx = null;
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			partOrders.addAll(session.createCriteria(PartOrder.class).list());
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
		}

		for (PartOrder partOrder : partOrders) {
			AllPartsInOrder allpart = new AllPartsInOrder();
			allpart.setAmount(partOrder.getAmount());
			allpart.setId(partOrder.getOrder().getOrderInWorkId());
			allpart.setPart_id(partOrder.getPart().getPartId());
			allpart.setPart_name(partOrder.getPart().getPart_name());
			allpart.setVendor(partOrder.getPart().getVendor());
			if (!results.contains(allpart)) {
				results.add(allpart);
			}
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<AllPartsInOrder> getAll(long orderid) {
		List<AllPartsInOrder> results = new ArrayList<>();
		List<PartOrder> partOrders = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			OrderInWork orderInWork = (OrderInWork) session.createCriteria(OrderInWork.class)
					.add(Restrictions.eq("order_id", orderid)).uniqueResult();
			partOrders
					.addAll(session.createCriteria(PartOrder.class).add(Restrictions.eq("order", orderInWork)).list());
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}

		for (PartOrder partOrder : partOrders) {
			AllPartsInOrder allpart = new AllPartsInOrder();
			allpart.setAmount(partOrder.getAmount());
			allpart.setId(partOrder.getOrder().getOrderInWorkId());
			allpart.setPart_id(partOrder.getPart().getPartId());
			allpart.setPart_name(partOrder.getPart().getPart_name());
			allpart.setVendor(partOrder.getPart().getVendor());
			if (!results.contains(allpart)) {
				results.add(allpart);
			}
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public AllPartsInOrder findByPartAndOrder(long orderid, long partid) {
		Session session = null;
		Transaction tx = null;
		PartOrder partOrder = null;
		AllPartsInOrder allDetailsPartInOrder = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			OrderInWork orderInWork = (OrderInWork) session.createCriteria(OrderInWork.class)
					.add(Restrictions.eq("order_id", orderid)).uniqueResult();
			Part part = (Part) session.createCriteria(Part.class).add(Restrictions.eq("part_id", partid))
					.uniqueResult();
			partOrder = (PartOrder) session.createCriteria(PartOrder.class).add(Restrictions.eq("order", orderInWork))
					.add(Restrictions.eq("part", part)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
		}

		if (partOrder != null) {
			allDetailsPartInOrder = new AllPartsInOrder();
			allDetailsPartInOrder.setAmount(partOrder.getAmount());
			allDetailsPartInOrder.setId(partOrder.getOrder().getOrderInWorkId());
			allDetailsPartInOrder.setPart_id(partOrder.getPart().getPartId());
			allDetailsPartInOrder.setPart_name(partOrder.getPart().getPart_name());
			allDetailsPartInOrder.setVendor(partOrder.getPart().getVendor());
		}

		return allDetailsPartInOrder;
	}

}
