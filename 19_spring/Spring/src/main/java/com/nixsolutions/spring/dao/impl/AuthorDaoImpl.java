package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.AuthorDAO;
import com.nixsolutions.spring.entity.Author;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
        sessionFactory.getCurrentSession().save("author", entity);
    }

    @Override
    public void update(Author entity) {
        sessionFactory.getCurrentSession().saveOrUpdate("author", entity);
    }

    @Override
    public void delete(Author entity) {
        sessionFactory.getCurrentSession().delete("author", entity);
    }

    @Override
    public Author findByID(Long id) {
        return (Author) sessionFactory.getCurrentSession().byId(Author.class).load(id);
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) sessionFactory.getCurrentSession().createCriteria(Author.class).list();
    }
}
