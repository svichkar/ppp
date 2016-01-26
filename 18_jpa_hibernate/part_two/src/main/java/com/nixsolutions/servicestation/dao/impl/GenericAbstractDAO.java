package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.GenericDAO;
import com.nixsolutions.servicestation.util.HiberUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Transient;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public abstract class GenericAbstractDAO<E> implements GenericDAO<E> {
    protected Class<E> type;
    public static SessionFactory sFactory = HiberUtil.getSessionFactory();

    public GenericAbstractDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public void create(E entity) {
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }

    public void update(E entity) {
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    public void delete(E entity) {
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    public E findById(Long id) {
        Session session = sFactory.getCurrentSession();
        E entity;
        Transaction transaction = session.beginTransaction();
        entity = (E) session.get(type, id);
        transaction.commit();
        return entity;
    }

    public Set<E> findAll() {
        Session session = sFactory.getCurrentSession();
        Set<E> set;
        Transaction transaction = session.beginTransaction();
        set = new HashSet<>(session.createCriteria(type).list());
        transaction.commit();
        return set;
    }
}
