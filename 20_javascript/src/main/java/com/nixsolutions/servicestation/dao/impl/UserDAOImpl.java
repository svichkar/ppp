package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.UserDAO;
import com.nixsolutions.servicestation.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by rybkinrolla on 06.01.2016.
 */

@Repository("userDao")
public class UserDAOImpl extends GenericAbstractDAO<User> implements UserDAO {

    @Override
    public User findByLogin(String login) {
        User user;
        user = (User) getCurrentSession().createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
        return user;
    }

}
