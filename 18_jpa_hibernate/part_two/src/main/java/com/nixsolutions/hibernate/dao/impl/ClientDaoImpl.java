package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.ClientDAO;
import com.nixsolutions.hibernate.entity.Client;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class ClientDaoImpl implements ClientDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Client entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Client entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Client entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Client client = null;
        Transaction transaction = session.beginTransaction();
        try {
            client = (Client) session.get(Client.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Client> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            list = session.createCriteria(Client.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return list;
    }
}
