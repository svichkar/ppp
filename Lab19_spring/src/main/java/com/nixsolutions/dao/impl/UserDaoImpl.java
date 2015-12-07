package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static Logger LOG = LogManager.getLogger(UserDaoImpl.class.getName());

    public UserDaoImpl() {
    }

    public void create(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void update(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public void delete(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
    }

    public User getByUserId(int userId) {
        User user = new User();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, userId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return user;
    }

    public User getByUserName(String userName) {
        User user = new User();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria c = session.createCriteria(User.class);
            c.add(Restrictions.eq("userName", userName));
            List list = c.list();
            if (list.size() == 0){
            	transaction.commit();
            	return null;
            }
            user = (User) list.get(0);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return user;
    }

    public List<User> getAll() {
        List<User> toReturn = new ArrayList<User>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            toReturn.addAll(session.createCriteria(User.class).list());
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return toReturn;
    }

    public boolean checkUser(String userName, String password) {
        boolean result = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria c = session.createCriteria(User.class);
            c.add(Restrictions.eq("userName", userName))
             .add(Restrictions.eq("password", password));
            List list = c.list();
            result = list.iterator().hasNext();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return result;
    }
}