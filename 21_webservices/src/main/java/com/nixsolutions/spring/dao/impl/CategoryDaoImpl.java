package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.CategoryDAO;
import com.nixsolutions.spring.entity.Category;
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
        sessionFactory.getCurrentSession().save("category", entity);
    }

    @Override
    public void update(Category entity) {
        sessionFactory.getCurrentSession().saveOrUpdate("category", entity);
    }

    @Override
    public void delete(Category entity) {
        sessionFactory.getCurrentSession().delete("category", entity);
    }

    @Override
    public Category findByID(Long id) {
        return (Category) sessionFactory.getCurrentSession().byId(Category.class).load(id);
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>) sessionFactory.getCurrentSession().createCriteria(Category.class).list();
    }
}
