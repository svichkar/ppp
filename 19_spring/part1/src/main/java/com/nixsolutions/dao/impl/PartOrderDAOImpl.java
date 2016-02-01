package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.PartOrderDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Part;
import com.nixsolutions.entities.PartOrder;
import com.nixsolutions.error.CustomDataException;

@Repository
@Transactional
public class PartOrderDAOImpl implements PartOrderDAO<PartOrder> {

	private final static Logger LOG = LogManager.getLogger(PartOrderDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;


	@Override
	public void create(PartOrder t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(PartOrder t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(PartOrder t) {
		sessionFactory.getCurrentSession().delete(t);
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
		partOrders.addAll(sessionFactory.getCurrentSession().createCriteria(PartOrder.class).list());
		return partOrders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartOrder> getAllForOrder(long order_id) {
		List<PartOrder> partOrders = new ArrayList<>();
		OrderInWork oiw = (OrderInWork) sessionFactory.getCurrentSession().createCriteria(OrderInWork.class)
				.add(Restrictions.eq("order_id", order_id)).uniqueResult();
		partOrders.addAll(sessionFactory.getCurrentSession().createCriteria(PartOrder.class)
				.add(Restrictions.eq("order", oiw)).list());
		return partOrders;
	}

	@Override
	public PartOrder findbyPartAndOrder(long order_id, long part_id) {
		OrderInWork oiw = (OrderInWork) sessionFactory.getCurrentSession().createCriteria(OrderInWork.class)
				.add(Restrictions.eq("order_id", order_id)).uniqueResult();
		Part part = (Part) sessionFactory.getCurrentSession().createCriteria(Part.class)
				.add(Restrictions.eq("part_id", part_id)).uniqueResult();
		return (PartOrder) sessionFactory.getCurrentSession().createCriteria(PartOrder.class)
				.add(Restrictions.eq("order", oiw)).add(Restrictions.eq("part", part)).uniqueResult();
	}

}
