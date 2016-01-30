package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.RoleDAO;
import com.nixsolutions.spring.entity.Role;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("roleDAO")
@Transactional
public class RoleDaoImpl implements RoleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Role entity) {
        try {
            sessionFactory.getCurrentSession().save("role", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Role entity) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate("role", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Role entity) {
        try {
            sessionFactory.getCurrentSession().delete("role", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role findByID(Long id) {
        Role entity = null;
        try {
            entity = (Role) sessionFactory.getCurrentSession().byId(Role.class).load(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public Role findByName(String name) {
        Role entity = null;
        try {
           entity = (Role) sessionFactory.getCurrentSession().createCriteria(Role.class).add(Restrictions.eq("name", name)).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = null;
        try {
            list = sessionFactory.getCurrentSession().createCriteria(Role.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
