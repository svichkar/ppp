package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.User;
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


    public void create(User user) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(user);
        transaction.commit();

        session.close();
    }

    public void update(User user) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);
        transaction.commit();

        session.close();
    }

    public void delete(User user) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.delete(user);
        transaction.commit();

        session.close();
    }

    public List<User> findAll() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<User> list = session.createCriteria(User.class).list();
        transaction.commit();
        session.close();

        return list;
    }

    public boolean validateUser(String user) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", user));
        List<Subject> results = criteria.list();
        transaction.commit();
        session.close();

        if (results.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public User getUserByLoginAndPassword(String user, String pass) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.and(Restrictions.eq("login", user), Restrictions.eq("userPassword", pass)));
        List<User> results = criteria.list();
        transaction.commit();
        session.close();

        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User findByLogin(String userLogin) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", userLogin));
        List<User> results = criteria.list();
        transaction.commit();
        session.close();

        if (results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

}
