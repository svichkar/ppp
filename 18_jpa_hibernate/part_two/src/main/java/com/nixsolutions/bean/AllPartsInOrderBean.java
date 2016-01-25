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
		List<AllPartsInOrder> listResults = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<PartOrder> lPO = session.createCriteria(PartOrder.class).list();
		tx.commit();

		for (PartOrder partOrder : lPO) {
			AllPartsInOrder allpart = new AllPartsInOrder();
			allpart.setAmount(partOrder.getAmount());
			allpart.setId(partOrder.getOrder().getOrderInWorkId());
			allpart.setPart_id(partOrder.getPart().getPartId());
			allpart.setPart_name(partOrder.getPart().getPart_name());
			allpart.setVendor(partOrder.getPart().getVendor());
			if (!listResults.contains(allpart)) {
				listResults.add(allpart);
			}
		}
		return listResults;
	}

	@SuppressWarnings("unchecked")
	public List<AllPartsInOrder> getAll(long order_id) {

		List<AllPartsInOrder> listResults = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class)
				.add(Restrictions.eq("order_id", order_id)).uniqueResult();
		List<PartOrder> lPO = session.createCriteria(PartOrder.class).add(Restrictions.eq("order", oiw)).list();
		tx.commit();

		for (PartOrder partOrder : lPO) {
			AllPartsInOrder allpart = new AllPartsInOrder();
			allpart.setAmount(partOrder.getAmount());
			allpart.setId(partOrder.getOrder().getOrderInWorkId());
			allpart.setPart_id(partOrder.getPart().getPartId());
			allpart.setPart_name(partOrder.getPart().getPart_name());
			allpart.setVendor(partOrder.getPart().getVendor());
			if (!listResults.contains(allpart)) {
				listResults.add(allpart);
			}
		}
		return listResults;
	}

	@SuppressWarnings("unchecked")
	public AllPartsInOrder findByPartAndOrder(long order_id, long part_id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class)
				.add(Restrictions.eq("order_id", order_id)).uniqueResult();
		Part part = (Part) session.createCriteria(Part.class).add(Restrictions.eq("part_id", part_id)).uniqueResult();
		PartOrder partOrder = (PartOrder) session.createCriteria(PartOrder.class).add(Restrictions.eq("order", oiw))
				.add(Restrictions.eq("part", part)).uniqueResult();
		tx.commit();
		AllPartsInOrder allpart = new AllPartsInOrder();
		allpart.setAmount(partOrder.getAmount());
		allpart.setId(partOrder.getOrder().getOrderInWorkId());
		allpart.setPart_id(partOrder.getPart().getPartId());
		allpart.setPart_name(partOrder.getPart().getPart_name());
		allpart.setVendor(partOrder.getPart().getVendor());

		return allpart;
	}

}
