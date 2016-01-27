package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.RoleDAO;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class RoleDaoImpl implements RoleDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Role entity) {
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
    public void update(Role entity) {
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
    public void delete(Role entity) {
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
    public Role findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Role role = null;
        Transaction transaction = session.beginTransaction();
        try {
            role = (Role) session.get(Role.class, id);
            transaction.commit();
            return role;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Role role = null;
        Transaction transaction = session.beginTransaction();
        try {
            role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("roleName", name)).uniqueResult();
            transaction.commit();
            return role;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Role> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            list = session.createCriteria(Role.class).list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }

    }
}
