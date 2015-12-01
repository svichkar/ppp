package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.entity.Worker;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class OrderInWorkDAOImpl implements OrderInWorkDAO {

	public static Logger LOG = LogManager.getLogger(OrderInWorkDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public OrderInWork createFrom(OrderInWork entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderInWork orderInWork = null;
		try {
			session.saveOrUpdate("orderInWork", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		try {
			orderInWork = (OrderInWork) session.get(OrderInWork.class, entity.getOrderId());
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWork;
	}

	@Override
	public void update(OrderInWork entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("orderInWork", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(OrderInWork entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("orderInWork", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public OrderInWork getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		OrderInWork orderInWork = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWork = (OrderInWork) session.byId(OrderInWork.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWork;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWorkList = session.createCriteria(OrderInWork.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
		orderInWorkList = session.createSQLQuery(
				"SELECT oiw.* FROM sqllab.order_in_work oiw " + 
				"INNER JOIN sqllab.car car ON oiw.car_id = car.car_id " + 
				"INNER JOIN sqllab.customer c ON car.customer_id = c.customer_id " + 
				"WHERE c.user_id = " + user.getUserId())
				.setResultTransformer(Transformers.aliasToBean(OrderInWork.class)).list();
		tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByCar(Car car) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWorkList = session.createCriteria(OrderInWork.class).add(Restrictions.eq("car.carId", car.getCarId()))
					.list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByPart(Part part) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWorkList = session.createSQLQuery(
				"SELECT oiw.* FROM sqllab.order_in_work oiw " + 
				"INNER JOIN sqllab.order_part op ON oiw.order_id = op.order_id " + 
				"WHERE op.part_id = " + part.getPartId())
				.setResultTransformer(Transformers.aliasToBean(OrderInWork.class)).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByWorker(Worker worker) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWorkList = session.createSQLQuery(
				"SELECT oiw.* FROM sqllab.order_in_work oiw " + 
				"INNER JOIN sqllab.order_worker ow ON oiw.order_id = ow.order_id " + 
				"WHERE ow.worker_id = " + worker.getWorkerId())
				.setResultTransformer(Transformers.aliasToBean(OrderInWork.class)).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWorkList = session.createSQLQuery(
				"SELECT oiw.* FROM sqllab.order_in_work oiw " + 
				"INNER JOIN sqllab.car car ON oiw.car_id = car.car_id " + 
				"WHERE car.customer_id = " + customer.getCustomerId())
				.setResultTransformer(Transformers.aliasToBean(OrderInWork.class)).list();
			tx.commit();
		} catch (Exception ex) {
		tx.rollback();
		LOG.error(ex);
		}
		return orderInWorkList;
	}

}
