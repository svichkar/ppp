package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.CellDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Cell;

@Repository("CellDao")
public class CellDaoImpl implements CellDao {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	public static SessionFactory sessionFactory;
	
	@Override
	public List<Cell> getAllCells() {
		LOG.entry();
		List<Cell> cells = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Cell.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cells = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

		return LOG.exit(cells);
	}

	@Override
	public Cell getCellById(Long cellId) {
		LOG.entry(cellId);
		Cell cell = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Cell.class)
					.add(Restrictions.eq("cellId", cellId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cell = (Cell) criteria.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}

		return LOG.exit(cell);
	}

	@Override
	public Cell getCellByName(String cellName) {
		LOG.entry(cellName);
		Cell cell = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Cell.class)
					.add(Restrictions.eq("name", cellName))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cell = (Cell) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(cell);
	}

	@Override
	public void createCell(Cell cell) {
		LOG.entry(cell);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(cell);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}

	@Override
	public void updateCell(Cell cell) {
		LOG.entry(cell);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.saveOrUpdate(cell);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}

	@Override
	public void deleteCell(Cell cell) {
		LOG.entry(cell);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.delete(cell);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}
}
