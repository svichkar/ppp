package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StatusDao;
import com.nixsolutions.studentgrade.model.Status;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@Repository
public class StatusDaoImpl implements StatusDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Status status) {

       sessionFactory.getCurrentSession().save(status);
    }

    @Override
    public void update(Status status) {

        sessionFactory.getCurrentSession().saveOrUpdate(status);
    }

    @Override
    public void delete(Status status) {

        sessionFactory.getCurrentSession().delete(status);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Status> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<Status> list = session.createCriteria(Status.class).list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Status findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Status status = (Status) session.get(Status.class, id);
        return status;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Status findByName(String statusName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Status.class);
        criteria.add(Restrictions.eq("statusName", statusName));
        Status status = (Status) criteria.uniqueResult();
        return status;
    }
}
