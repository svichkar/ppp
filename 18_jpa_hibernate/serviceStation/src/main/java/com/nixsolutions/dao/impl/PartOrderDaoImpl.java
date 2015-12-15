/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.PartOrderDao;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.PartOrder;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author mixeyes
 *
 */
public class PartOrderDaoImpl implements PartOrderDao {

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#getAllParts()
	 */
	@Override
	public List<PartOrder> getAllParts() {
		List<PartOrder> part_orders = new ArrayList<PartOrder>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			part_orders = session.createCriteria(PartOrder.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return part_orders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#
	 * getPartsByOrderId(java.lang.Integer)
	 */
	@Override
	public List<PartOrder> getPartsByOrderId(Integer order_id) {
		List<PartOrder> part_orders = new ArrayList<PartOrder>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			part_orders = session.createCriteria(PartOrder.class,"pOrder").createAlias("pOrder.order", "order")
					.add(Restrictions.eq("order.orderId", order_id))
					.list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return part_orders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#
	 * getPartsByOrderId(java.lang.Integer)
	 */
	@Override
	public PartOrder getPartByOrderId(Integer order_id, Integer part_id) {
		PartOrder part_order = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			part_order = (PartOrder) session.createCriteria(PartOrder.class,"pOrder")
					.createAlias("pOrder.order", "order")
					.createAlias("pOrder.part", "part")
					.add(Restrictions.eq("order.orderId", order_id)).add(Restrictions.eq("part.partId", part_id))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return part_order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#setPartToOrder(
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void setPartToOrder(Integer order_id, Integer part_id, Integer amount) {
		PartOrder partOrder = new PartOrder();
		OrderInWork orderInWork = DaoFactory.getOrderInWorkDaoImpl().getOrderByID(order_id);
		Part part = DaoFactory.getPartDaoImpl().getPart(part_id);
		partOrder.setAmount(amount);
		partOrder.setOrder(orderInWork);
		partOrder.setPart(part);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(partOrder);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}finally {
			if(session.isOpen())
				session.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#setPartToOrder(
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void updatePartOrder(Integer order_id, Integer part_id, Integer amount) {
		PartOrder partOrder =getPartByOrderId(order_id,part_id);
		OrderInWork orderInWork = DaoFactory.getOrderInWorkDaoImpl().getOrderByID(order_id);
		Part part = DaoFactory.getPartDaoImpl().getPart(part_id);
		partOrder.setAmount(amount);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(partOrder);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}finally {
			if(session.isOpen())
				session.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#
	 * deletePartFromOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean deletePartFromOrder(Integer order_id, Integer part_id) {
		PartOrder part_order = getPartByOrderId(order_id, part_id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(part_order);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
		part_order = getPartByOrderId(order_id, part_id);
		if (part_order == null)
			return true;
		else
			return false;
	}
}
