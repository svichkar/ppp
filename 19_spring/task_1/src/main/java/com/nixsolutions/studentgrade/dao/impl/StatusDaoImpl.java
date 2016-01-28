package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StatusDao;
import com.nixsolutions.studentgrade.model.Status;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public class StatusDaoImpl implements StatusDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Status status) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(status);
        transaction.commit();
    }

    @Override
    public void update(Status status) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(status);
        transaction.commit();
    }

    @Override
    public void delete(Status status) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(status);
        transaction.commit();
    }

    @Override
    public List<Status> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Status> list = session.createCriteria(Status.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public Status findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Status status = (Status) session.get(Status.class, id);
        transaction.commit();
        return status;
    }

    @Override
    public Status findByName(String status) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Status.class);
        criteria.add(Restrictions.eq("statusName", status)).uniqueResult();
        List<Status> statuses = criteria.list();
        transaction.commit();
        return statuses.isEmpty() ? null : statuses.get(0);
    }
}
