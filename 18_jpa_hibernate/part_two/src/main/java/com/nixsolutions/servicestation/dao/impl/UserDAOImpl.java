package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.UserDAO;
import com.nixsolutions.servicestation.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
public class UserDAOImpl extends GenericAbstractDAO<User> implements UserDAO {

    @Override
    public User findByLogin(String login) {
        Session session = sFactory.getCurrentSession();
        User user;
        Transaction transaction = session.beginTransaction();
        user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
        transaction.commit();
        return user;
    }

    public Set<User> findClientsUsers() {
        Set<User> userList;
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class, "u");
        criteria.createAlias("u.client", "client");
        criteria.createAlias("u.role", "role");
        userList = new HashSet<User>(criteria.list());
        transaction.commit();
        return userList;
    }
}
