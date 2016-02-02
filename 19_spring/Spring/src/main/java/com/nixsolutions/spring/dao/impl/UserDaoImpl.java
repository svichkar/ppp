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
        sessionFactory.getCurrentSession().save("user", entity);
    }


    @Override
    public void update(User entity) {
        sessionFactory.getCurrentSession().saveOrUpdate("user", entity);
    }

    @Override
    public void delete(User entity) {
        sessionFactory.getCurrentSession().delete("user", entity);
    }

    @Override
    public User findByID(Long id) {
        return (User) sessionFactory.getCurrentSession().byId(User.class).load(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    @Override
    public User findByLogin(String login) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
    }
}
