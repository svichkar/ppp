package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.BookDAO;
import com.nixsolutions.hibernate.entity.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("bookDAO")
@Transactional
public class BookDaoImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Book entity) {
        try {
            sessionFactory.getCurrentSession().save("book", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Book entity) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate("book", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Book entity) {
        try {
            sessionFactory.getCurrentSession().delete("book", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findByID(Long id) {
        Book entity = null;
        try {
            entity = (Book) sessionFactory.getCurrentSession().byId(Book.class).load(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<Book> findAll() {
        List<Book> list = null;
        try {
            list = sessionFactory.getCurrentSession().createCriteria(Book.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Book> findByName(String bookName) {
        List<Book> list = null;
        try {
            list = sessionFactory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("bookName", bookName)).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        List<Book> list = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(Book.class, "book");
            criteria.createAlias("book.authors", "author");
            criteria.add(Restrictions.eq("author.authorLastName", authorName));
            list = criteria.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Book> findByCategory(String categoryName) {
        List<Book> list = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(Book.class, "book");
            criteria.createAlias("book.category", "category");
            criteria.add(Restrictions.eq("category.categoryName", categoryName));
            list = criteria.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
