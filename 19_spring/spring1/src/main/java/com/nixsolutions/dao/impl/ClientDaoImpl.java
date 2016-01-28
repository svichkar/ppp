package com.nixsolutions.dao.impl;

import java.util.ArrayList;
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

import com.nixsolutions.dao.ClientDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.entity.Client;

@Repository("ClientDao")
public class ClientDaoImpl implements ClientDao {
	public static final Logger LOG = LogManager.getLogger();	
	@Autowired
	public static SessionFactory sessionFactory;
	
	@Override
	public List<Client> getAllClients() {
		LOG.entry();
		List<Client> clients = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Client.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			clients = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(clients);
	}

	@Override
	public Client getClientById(Long clientId) {
		LOG.entry(clientId);
		Client client = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Client.class)
					.add(Restrictions.eq("clientId", clientId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			client = (Client) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(client);
	}

	@Override
	public void createClient(Client client) {
		LOG.entry(client);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.save(client);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}

	@Override
	public void updateClient(Client client) {
		LOG.entry(client);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.saveOrUpdate(client);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}

	@Override
	public void deleteClient(Client client) {
		LOG.entry(client);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			session.delete(client);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
	}

	@Override
	public Client getClientByName(String readerName) {
		LOG.entry(readerName);
		Client client = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Client.class, "client")
					.add(Restrictions.eq("client.secondName", readerName))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			client = (Client) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(client);
	}

	@Override
	public List<Client> getClientsByName(String readerName) {
		LOG.entry(readerName);
		List<Client> clients = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		try {
			Criteria criteria = session.createCriteria(Client.class, "client")
					.add(Restrictions.eq("client.secondName", readerName))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			clients = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			LOG.throwing(new DaoException("not able to finish transaction", e));
		}
		return LOG.exit(clients);
	}
}
