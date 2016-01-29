package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.CategoryDAO;
import com.nixsolutions.hibernate.entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("categoryDAO")
@Transactional
public class CategoryDaoImpl implements CategoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Category entity) {
        try {
            sessionFactory.getCurrentSession().save("category", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Category entity) {

        try {
            sessionFactory.getCurrentSession().saveOrUpdate("category", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Category entity) {

        try {
            sessionFactory.getCurrentSession().delete("category", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category findByID(Long id) {
        Category entity = null;
        try {
            entity = (Category) sessionFactory.getCurrentSession().byId(Category.class).load(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = null;
        try {
            list = sessionFactory.getCurrentSession().createCriteria(Category.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
