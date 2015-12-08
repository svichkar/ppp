package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.entity.Part;

@Repository("orderPartDAO")
@Transactional
public class OrderPartDAOImpl implements OrderPartDAO {

	public static Logger LOG = LogManager.getLogger(OrderPartDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(OrderPart entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("orderPart", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(OrderPart entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("orderPart", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(OrderPart entity) {
		try {
			sessionFactory.getCurrentSession().delete("orderPart", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public OrderPart getByPK(int id) {
		try {
			return (OrderPart) sessionFactory.getCurrentSession().byId(OrderPart.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPart> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderPart.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@Override
	public OrderPart getByPK(long orderId, long partId) {
		try {
			return (OrderPart) sessionFactory.getCurrentSession().createCriteria(OrderPart.class)
					.add(Restrictions.eq("order.orderId", orderId))
					.add(Restrictions.eq("part.partId", partId))
					.uniqueResult();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPart> getByOrderId(long orderId) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderPart.class)
					.add(Restrictions.eq("order.orderId", orderId))
					.list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPart> getOrderPartByPart(Part part) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderPart.class)
					.add(Restrictions.eq("part.partId", part.getPartId()))
					.list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

}
