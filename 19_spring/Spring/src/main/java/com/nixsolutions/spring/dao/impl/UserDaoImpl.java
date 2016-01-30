package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.UserDAO;
import com.nixsolutions.spring.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User entity) {
        try {
            sessionFactory.getCurrentSession().save("user", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(User entity) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate("user", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User entity) {
        try {
            sessionFactory.getCurrentSession().delete("user", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByID(Long id) {
        User entity = null;
        try {
            entity = (User) sessionFactory.getCurrentSession().byId(User.class).load(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<User> findAll() {
        List<User> list = null;
        try {
            list = sessionFactory.getCurrentSession().createCriteria(User.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public User findByLogin(String login) {
        User entity = null;
        try {
            entity = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } return entity;
    }
}
