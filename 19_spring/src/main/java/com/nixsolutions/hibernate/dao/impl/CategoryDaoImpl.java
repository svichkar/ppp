package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.CategoryDAO;
import com.nixsolutions.hibernate.entity.Category;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("categoryDAO")
@Transactional
public class CategoryDaoImpl implements CategoryDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Category entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Category entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Category entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = null;
        Transaction transaction = session.beginTransaction();
        try {
            category = (Category) session.get(Category.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            list = session.createCriteria(Category.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return list;
    }
}
