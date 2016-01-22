package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StatusDao;
import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class StatusDaoImpl implements StatusDao {

    @Override
    public void create(Status status) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(status);
        transaction.commit();


    }

    @Override
    public void update(Status status) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(status);
        transaction.commit();


    }

    @Override
    public void delete(Status status) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.delete(status);
        transaction.commit();


    }

    @Override
    public List<Status> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Status> list = session.createCriteria(Status.class).list();
        transaction.commit();


        return list;
    }

    @Override
    public Status findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Status.class);
        criteria.add(Restrictions.idEq(id));
        List<Status> results = criteria.list();
        transaction.commit();


        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Status findByName(String statusName) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Status.class);
        criteria.add(Restrictions.eq("statusName", statusName));
        List<Status> results = criteria.list();
        transaction.commit();


        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
