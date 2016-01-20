package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.UserDAO;
import com.nixsolutions.servicestation.entity.User;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

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

    public List<User> findClientsUsers() {
        List<User> userList = new ArrayList<>();
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class, "u");
        transaction.commit();
        return userList;
    }
}
