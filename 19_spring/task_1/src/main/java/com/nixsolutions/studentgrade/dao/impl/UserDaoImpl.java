package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(User user) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    @Transactional
    public void update(User user) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
    }

    @Override
    @Transactional
    public void delete(User user) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
    }

    @Override
    public List<User> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createCriteria(User.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public User findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        transaction.commit();
        return user;
    }

    @Override
    public boolean validateUser(String user) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", user));
        List<User> users = criteria.list();
        transaction.commit();
        return !users.isEmpty();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String pass) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.and(Restrictions.eq("login", login), Restrictions.eq("userPassword", pass)));
        List<User> users = criteria.list();
        transaction.commit();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByLogin(String login) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login)).uniqueResult();
        List<User> users = criteria.list();
        transaction.commit();
        return users.isEmpty() ? null : users.get(0);
    }
}
