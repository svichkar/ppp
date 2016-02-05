package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.model.User;
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
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {

        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {

        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void delete(User user) {

        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createCriteria(User.class).list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean validateUser(String user) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", user));
        List<User> users = criteria.list();
        return !users.isEmpty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByLoginAndPassword(String login, String pass) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.and(Restrictions.eq("login", login), Restrictions.eq("userPassword", pass)));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findByLogin(String login) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isUnique(User user) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.or(Restrictions.eq("login", user.getLogin()),
                Restrictions.eq("email", user.getEmail())));

        return criteria.list().isEmpty() ? true : false;
    }
}
