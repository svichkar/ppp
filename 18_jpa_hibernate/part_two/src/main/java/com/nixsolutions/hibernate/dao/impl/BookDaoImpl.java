package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.BookDAO;
import com.nixsolutions.hibernate.entity.Book;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class BookDaoImpl implements BookDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public void create(Book entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }


    @Override
    public void update(Book entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    @Override
    public void delete(Book entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    @Override
    public Book findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = null;
        Transaction transaction = session.beginTransaction();
        book = (Book) session.get(Book.class, id);
        transaction.commit();
        return book;
    }

    @Override
    public List<Book> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = null;
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(Book.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public List<Book> findByName(String bookName) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = null;
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(Book.class).add(Restrictions.eq("bookName", bookName)).list();
        transaction.commit();
        return list;
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = null;
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Book.class, "book");
        criteria.createAlias("book.authors", "author");
        criteria.add(Restrictions.eq("author.authorLastName", authorName));
        list = criteria.list();
        transaction.commit();
        return list;
    }

    @Override
    public List<Book> findByCategory(String categoryName) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = null;
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(Book.class).add(Restrictions.eq("category.categoryName", categoryName)).list();
        transaction.commit();
        return list;
    }
}
