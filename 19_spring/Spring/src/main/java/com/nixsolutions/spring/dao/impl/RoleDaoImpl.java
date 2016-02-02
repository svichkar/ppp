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
        sessionFactory.getCurrentSession().save("role", entity);
    }

    @Override
    public void update(Role entity) {
        sessionFactory.getCurrentSession().saveOrUpdate("role", entity);
    }

    @Override
    public void delete(Role entity) {
        sessionFactory.getCurrentSession().delete("role", entity);
    }

    @Override
    public Role findByID(Long id) {
        return (Role) sessionFactory.getCurrentSession().byId(Role.class).load(id);
    }

    @Override
    public Role findByName(String name) {
        return (Role) sessionFactory.getCurrentSession().createCriteria(Role.class).add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) sessionFactory.getCurrentSession().createCriteria(Role.class).list();
    }
}
