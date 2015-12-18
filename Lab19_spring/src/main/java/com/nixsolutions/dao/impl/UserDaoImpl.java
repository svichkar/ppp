package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private static Logger LOG = LogManager.getLogger(UserDaoImpl.class.getName());
    @Autowired
	private SessionFactory sessionFactory;
    
    public UserDaoImpl() {
    }

    public void create(User user) {
        try {
            sessionFactory.getCurrentSession().save(user);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public void update(User user) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(user);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public void delete(User user) {
        try {
            sessionFactory.getCurrentSession().delete(user);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public User getByUserId(int userId) {
        User user = new User();
        try {
            user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
        } catch (Exception ex) {
            LOG.error(ex);
        }
        return user;
    }

    @SuppressWarnings("unchecked")
	public User getByUserName(String userName) {
        User user = new User();
        try {
            Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
            c.add(Restrictions.eq("userName", userName));
            List<User> list = c.list();
            if (list.size() == 0){
            	return null;
            }
            user = (User) list.get(0);
        } catch (Exception ex) {
            LOG.error(ex);
        }
        return user;
    }

    @SuppressWarnings("unchecked")
	public List<User> getAll() {
        List<User> toReturn = new ArrayList<User>();
        try {
            toReturn.addAll(sessionFactory.getCurrentSession().createCriteria(User.class).list());
        } catch (Exception ex) {
            LOG.error(ex);
        }
        return toReturn;
    }

    @SuppressWarnings("rawtypes")
	public boolean checkUser(String userName, String password) {
        boolean result = false;
        try {
        	Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
            c.add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password));
            List list = c.list();
            result = list.iterator().hasNext();
        } catch (Exception ex) {
            LOG.error(ex);
        }
        return result;
    }
}