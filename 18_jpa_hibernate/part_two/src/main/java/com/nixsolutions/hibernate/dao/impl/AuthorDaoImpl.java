package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.AuthorDAO;
import com.nixsolutions.hibernate.entity.Author;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class AuthorDaoImpl implements AuthorDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public void create(Author entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }


    @Override
    public void update(Author entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    @Override
    public void delete(Author entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    @Override
    public Author findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Author author = null;
        Transaction transaction = session.beginTransaction();
        author = (Author) session.get(Author.class, id);
        transaction.commit();
        return author;
    }

    @Override
    public List<Author> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Author> list = null;
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(Author.class).list();
        transaction.commit();
        return list;
    }
}
