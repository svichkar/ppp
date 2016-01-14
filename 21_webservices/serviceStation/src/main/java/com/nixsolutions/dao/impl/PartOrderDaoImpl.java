/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.PartOrderDao;
import com.nixsolutions.entity.PartOrder;

/**
 * @author mixeyes
 *
 */
@Repository("partOrderDao")
@Transactional
public class PartOrderDaoImpl implements PartOrderDao {

	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#getAllParts()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PartOrder> getAllParts() {
		List<PartOrder> part_orders = new ArrayList<PartOrder>();
		try {
			part_orders = sessionFactory.getCurrentSession().createCriteria(PartOrder.class).list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return part_orders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#
	 * getPartsByOrderId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PartOrder> getPartsByOrderId(Integer orderId) {
		List<PartOrder> part_orders = new ArrayList<PartOrder>();
		try {
			part_orders = sessionFactory.getCurrentSession().createCriteria(PartOrder.class, "pOrder")
					.createAlias("pOrder.order", "order").add(Restrictions.eq("order.orderId", orderId)).list();
		} catch (Exception ex) {
			logger.error(ex);
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
	public PartOrder getPartByOrderId(Integer orderId, Integer partId) {
		try {
			return (PartOrder) sessionFactory.getCurrentSession().createCriteria(PartOrder.class, "pOrder")
					.createAlias("pOrder.order", "order").createAlias("pOrder.part", "part")
					.add(Restrictions.eq("order.orderId", orderId)).add(Restrictions.eq("part.partId", partId))
					.uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#setPartToOrder(
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void saveOrUpdatePartOrder(PartOrder partOrder) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(partOrder);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#
	 * deletePartFromOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void deletePartFromOrder(PartOrder partOrder) {
		try {
			sessionFactory.getCurrentSession().delete(partOrder);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}
}
