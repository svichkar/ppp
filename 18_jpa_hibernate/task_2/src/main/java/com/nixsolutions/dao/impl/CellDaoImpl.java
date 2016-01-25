package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.CellDao;
import com.nixsolutions.entity.Cell;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class CellDaoImpl implements CellDao{
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Cell> getAllCells() {
		LOG.entry();
		List<Cell> cells = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Cell.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		cells = criteria.list();
		transaction.commit(); 
		return LOG.exit(cells);
	}

	@Override
	public Cell getCellById(Long cellId) {
		LOG.entry(cellId);
		Cell cell = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Cell.class)
				.add(Restrictions.eq("cellId", cellId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		cell = (Cell) criteria.uniqueResult();
		transaction.commit(); 
		return LOG.exit(cell);
	}

	@Override
	public Cell getCellByName(String cellName) {
		LOG.entry(cellName);
		Cell cell = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Cell.class)
				.add(Restrictions.eq("name", cellName)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		cell = (Cell) criteria.uniqueResult();
		transaction.commit(); 
		return LOG.exit(cell);
	}
	
	@Override
	public void createCell(Cell cell) {
		LOG.entry(cell);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(cell);
		transaction.commit(); 
	}

	@Override
	public void updateCell(Cell cell) {
		LOG.entry(cell);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.saveOrUpdate(cell);
		transaction.commit(); 
	}

	@Override
	public void deleteCell(Cell cell) {
		LOG.entry(cell);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(cell);
		transaction.commit(); 
	}
}
