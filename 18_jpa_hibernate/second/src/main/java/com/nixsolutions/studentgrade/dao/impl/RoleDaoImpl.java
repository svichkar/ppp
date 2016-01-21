package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by svichkar on 12/23/2015.
 */
public class RoleDaoImpl implements RoleDao {

    @Override
    public void create(Role role) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(role);
        transaction.commit();

        session.close();
    }

    @Override
    public void update(Role role) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(role);
        transaction.commit();

        session.close();
    }

    @Override
    public void delete(Role role) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.delete(role);
        transaction.commit();

        session.close();
    }

    public List<Role> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Role> list = session.createCriteria(Role.class).list();
        transaction.commit();
        session.close();

        return list;
    }

    public Role findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.idEq(id));
        List<Role> results = criteria.list();
        transaction.commit();
        session.close();

        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    public Role findByName(String role) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("roleName", role));
        List<Role> results = criteria.list();
        transaction.commit();
        session.close();

        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
