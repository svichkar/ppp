package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.ClientDao;
import com.nixsolutions.entity.Client;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class ClientDaoImpl implements ClientDao {
	public static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Client> getAllClients() {
		LOG.entry();
		List<Client> clients = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Client.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		clients = criteria.list();
		transaction.commit();
		return LOG.exit(clients);
	}

	@Override
	public Client getClientById(Long clientId) {
		LOG.entry(clientId);
		Client client = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Client.class)
				.add(Restrictions.eq("clientId", clientId))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		client = (Client) criteria.uniqueResult();
		transaction.commit();
		return LOG.exit(client);
	}

	@Override
	public void createClient(Client client) {
		LOG.entry(client);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(client);
		transaction.commit();
	}

	@Override
	public void updateClient(Client client) {
		LOG.entry(client);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.saveOrUpdate(client);
		transaction.commit();
	}

	@Override
	public void deleteClient(Client client) {
		LOG.entry(client);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(client);
		transaction.commit();
	}

	@Override
	public Client getClientByName(String readerName) {
		LOG.entry(readerName);
		Client client = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Client.class, "client")
				.add(Restrictions.eq("client.secondName", readerName))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		client = (Client) criteria.uniqueResult();
		transaction.commit();
		return LOG.exit(client);
	}

	@Override
	public List<Client> getClientsByName(String readerName) {
		LOG.entry(readerName);
		List<Client> clients = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Criteria criteria = session.createCriteria(Client.class, "client")
				.add(Restrictions.eq("client.secondName", readerName))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		clients = criteria.list();
		transaction.commit();
		return LOG.exit(clients);
	}

}
