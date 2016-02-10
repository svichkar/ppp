package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public abstract class GenericAbstractDAO<E> implements GenericDAO<E> {
    @Autowired
    private SessionFactory sFactory;

    protected Class<E> type;

    public GenericAbstractDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public void create(E entity) {
        getCurrentSession().clear();
        getCurrentSession().save(entity);
    }

    public void update(E entity) {
        getCurrentSession().clear();
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(E entity) {
        getCurrentSession().clear();
        getCurrentSession().delete(entity);
    }

    public E findById(Long id) {
        getCurrentSession().clear();
        E entity;
        entity = (E) getCurrentSession().get(type, id);
        return entity;
    }

    public Set<E> findAll() {
        getCurrentSession().clear();
        Set<E> set;
        set = new HashSet<>(getCurrentSession().createCriteria(type).list());
        return set;
    }

    protected Session getCurrentSession() {
        return sFactory.getCurrentSession();
    }
}
