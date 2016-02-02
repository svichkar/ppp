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
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.RentJournalDao;
import com.nixsolutions.entity.RentJournal;

@Repository("rentJournalDao")
public class RentJournalDaoImpl implements RentJournalDao {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<RentJournal> getAllRents() {
		LOG.entry();
		List<RentJournal> rentJournals = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(RentJournal.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			rentJournals = criteria.list();

		return LOG.exit(rentJournals);
	}

	@Override
	public RentJournal getRentById(Long rentId) {
		LOG.entry(rentId);
		RentJournal rentJournal = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(RentJournal.class)
					.add(Restrictions.eq("rentId", rentId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			rentJournal = (RentJournal) criteria.uniqueResult();

		return LOG.exit(rentJournal);
	}

	@Override
	public void createRent(RentJournal rentJournal) {
		LOG.entry(rentJournal.toString());
		Session session = sessionFactory.getCurrentSession();
			session.save(rentJournal);
	}

	@Override
	public void updateRent(RentJournal rentJournal) {
		LOG.entry(rentJournal);
		Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(rentJournal);
	}

	@Override
	public void deleteRent(RentJournal rentJournal) {
		LOG.entry(rentJournal);
		Session session = sessionFactory.getCurrentSession();
			session.delete(rentJournal);
	}

	@Override
	public List<RentJournal> getRentsByClientId(Long clientId) {
		LOG.entry(clientId);
		List<RentJournal> rentJournals = null;
		Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(RentJournal.class, "journal")
					.createAlias("journal.client", "client")
					.add(Restrictions.eq("client.clientId", clientId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			rentJournals = criteria.list();
		return LOG.exit(rentJournals);
	}
}
