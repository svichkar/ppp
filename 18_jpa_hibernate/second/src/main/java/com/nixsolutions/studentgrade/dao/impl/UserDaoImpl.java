package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.exception.CustomDaoException;
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
public class UserDaoImpl implements UserDao {

    @Override
    public void create(User user) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public void update(User user) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public void delete(User user) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
    }

    @Override
    public List<User> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<User> list;
        try {
            list = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return list;
    }

    @Override
    public User findById(Long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user;
        try {
            user = (User) session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return user;
    }

    @Override
    public boolean validateUser(String user) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", user));
        List<Subject> results;
        try {
            results = criteria.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return !results.isEmpty();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String pass) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.and(Restrictions.eq("login", login), Restrictions.eq("userPassword", pass)));
        User user;
        try {
            user = (User) criteria.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return user;
    }

    @Override
    public User findByLogin(String userLogin) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", userLogin));
        User user;
        try {
            user = (User) criteria.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CustomDaoException(e);
        }
        return user;
    }

}
