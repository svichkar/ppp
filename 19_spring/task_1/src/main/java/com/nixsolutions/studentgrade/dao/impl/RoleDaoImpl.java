package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.model.Role;
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
@Transactional
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Role role) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(role);
        transaction.commit();
    }

    @Override
    public void update(Role role) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(role);
        transaction.commit();
    }

    @Override
    public void delete(Role role) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
    }

    @Override
    public List<Role> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Role> list = session.createCriteria(Role.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public Role findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Role role = (Role) session.get(Role.class, id);
        transaction.commit();
        return role;
    }

    @Override
    public Role findByName(String role) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("roleName", role)).uniqueResult();
        List<Role> roles = criteria.list();
        transaction.commit();
        return roles.isEmpty() ? null : roles.get(0);
    }
}
