package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.AuthorDAO;
import com.nixsolutions.spring.entity.Author;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("authorDAO")
@Transactional
public class AuthorDaoImpl implements AuthorDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void create(Author entity) {
        try {
            sessionFactory.getCurrentSession().save("author", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Author entity) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate("author", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Author entity) {
        try {
            sessionFactory.getCurrentSession().delete("author", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author findByID(Long id) {
        Author entity = null;
        try {
            entity = (Author) sessionFactory.getCurrentSession().byId(Author.class).load(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<Author> findAll() {
        List<Author> list = null;
        try {
            list = sessionFactory.getCurrentSession().createCriteria(Author.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
