package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.PartOrderDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Part;
import com.nixsolutions.entities.PartOrder;

@Repository
@Transactional
public class PartOrderDAOImpl implements PartOrderDAO {

	@Autowired
	protected SessionFactory sessionFactory;

	public void create(PartOrder t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void update(PartOrder t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void delete(PartOrder t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Deprecated
	public PartOrder findByPK(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PartOrder> getAll() {
		List<PartOrder> partOrders = new ArrayList<>();
		partOrders.addAll(sessionFactory.getCurrentSession().createCriteria(PartOrder.class).list());
		return partOrders;
	}

	@SuppressWarnings("unchecked")
	public List<PartOrder> getAllForOrder(long orderId) {
		List<PartOrder> partOrders = new ArrayList<>();
		OrderInWork orderInWork = (OrderInWork) sessionFactory.getCurrentSession().createCriteria(OrderInWork.class)
				.add(Restrictions.eq("orderId", orderId)).uniqueResult();
		partOrders.addAll(sessionFactory.getCurrentSession().createCriteria(PartOrder.class)
				.add(Restrictions.eq("order", orderInWork)).list());
		return partOrders;
	}

	@Override
	public PartOrder findbyPartAndOrder(long orderId, long partId) {
		OrderInWork orderInWork = (OrderInWork) sessionFactory.getCurrentSession().createCriteria(OrderInWork.class)
				.add(Restrictions.eq("orderId", orderId)).uniqueResult();
		Part part = (Part) sessionFactory.getCurrentSession().createCriteria(Part.class)
				.add(Restrictions.eq("partId", partId)).uniqueResult();
		return (PartOrder) sessionFactory.getCurrentSession().createCriteria(PartOrder.class)
				.add(Restrictions.eq("order", orderInWork)).add(Restrictions.eq("part", part)).uniqueResult();
	}

}
