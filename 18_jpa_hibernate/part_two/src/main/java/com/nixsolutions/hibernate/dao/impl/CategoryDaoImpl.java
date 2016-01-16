package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.CategoryDAO;
import com.nixsolutions.hibernate.entity.Category;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class CategoryDaoImpl implements CategoryDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public void create(Category entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }


    @Override
    public void update(Category entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    @Override
    public void delete(Category entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    @Override
    public Category findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = null;
        Transaction transaction = session.beginTransaction();
        category = (Category) session.get(Category.class, id);
        transaction.commit();
        return category;
    }

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> list = null;
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(Category.class).list();
        transaction.commit();
        return list;
    }
}
