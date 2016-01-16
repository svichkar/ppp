package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.UserDAO;
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
public class UserDaoImpl implements UserDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public void create(User entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }


    @Override
    public void update(User entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    @Override
    public void delete(User entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    @Override
    public User findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = null;
        Transaction transaction = session.beginTransaction();
        user = (User) session.get(User.class, id);
        transaction.commit();
        return user;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = null;
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(User.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public User findByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        User user = null;
        Transaction transaction = session.beginTransaction();
        user = (User)session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
        transaction.commit();
        return user;
    }
}
