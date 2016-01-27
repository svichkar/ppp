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
        try {
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Author entity) {
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
    public void delete(Author entity) {
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
    public Author findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Author author = null;
        Transaction transaction = session.beginTransaction();
        try {
            author = (Author) session.get(Author.class, id);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } return author;
    }

    @Override
    public List<Author> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Author> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            list = session.createCriteria(Author.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return list;
    }
}
