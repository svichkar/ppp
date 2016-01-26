package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.RentJournalDao;
import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class RentJournalDaoImpl implements RentJournalDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<RentJournal> getAllRents() {
		LOG.entry();
		List<RentJournal> rentJournals = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(RentJournal.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		rentJournals = criteria.list();
		transaction.commit();
		return LOG.exit(rentJournals);
	}

	@Override
	public RentJournal getRentById(Long rentId) {
		LOG.entry(rentId);
		RentJournal rentJournal = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(RentJournal.class)
				.add(Restrictions.eq("rentId", rentId))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		rentJournal = (RentJournal) criteria.uniqueResult();
		transaction.commit();
		return LOG.exit(rentJournal);
	}

	@Override
	public void createRent(RentJournal rentJournal) {
		LOG.entry(rentJournal.toString());
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(rentJournal);
		transaction.commit();
	}

	@Override
	public void updateRent(RentJournal rentJournal) {
		LOG.entry(rentJournal);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.saveOrUpdate(rentJournal);
		transaction.commit();
	}

	@Override
	public void deleteRent(RentJournal rentJournal) {
		LOG.entry(rentJournal);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(rentJournal);
		transaction.commit();
	}

	@Override
	public List<RentJournal> getRentsByClientId(Long clientId) {
		LOG.entry(clientId);
		List<RentJournal> rentJournals = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(RentJournal.class, "journal")
				.createAlias("journal.client", "client")
				.add(Restrictions.eq("client.clientId", clientId))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		rentJournals = criteria.list();
		transaction.commit();
		return LOG.exit(rentJournals);
	}
}
