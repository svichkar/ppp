package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.model.Role;
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
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Role role) {

        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public void update(Role role) {

        sessionFactory.getCurrentSession().saveOrUpdate(role);
    }

    @Override
    public void delete(Role role) {

        sessionFactory.getCurrentSession().delete(role);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<Role> list = session.createCriteria(Role.class).list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Role findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Role role = (Role) session.get(Role.class, id);
        return role;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Role findByName(String roleName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("roleName", roleName));
        Role role = (Role) criteria.uniqueResult();
        return role;
    }
}
