package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.BookDAO;
import com.nixsolutions.hibernate.entity.Book;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("bookDAO")
@Transactional
public class BookDaoImpl implements BookDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Book entity) {
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
    public void update(Book entity) {
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
    public void delete(Book entity) {
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
    public Book findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = null;
        Transaction transaction = session.beginTransaction();
        try {
            book = (Book) session.get(Book.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            list = session.createCriteria(Book.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Book> findByName(String bookName) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            list = session.createCriteria(Book.class).add(Restrictions.eq("bookName", bookName)).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Book.class, "book");
            criteria.createAlias("book.authors", "author");
            criteria.add(Restrictions.eq("author.authorLastName", authorName));
            list = criteria.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Book> findByCategory(String categoryName) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Book.class, "book");
            criteria.createAlias("book.category", "category");
            criteria.add(Restrictions.eq("category.categoryName", categoryName));
            list = criteria.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return list;
    }
}
