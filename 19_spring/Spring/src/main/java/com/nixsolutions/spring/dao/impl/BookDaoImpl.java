package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.BookDAO;
import com.nixsolutions.spring.entity.Book;
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
        sessionFactory.getCurrentSession().save("book", entity);
    }

    @Override
    public void update(Book entity) {
        sessionFactory.getCurrentSession().saveOrUpdate("book", entity);
    }

    @Override
    public void delete(Book entity) {
        sessionFactory.getCurrentSession().delete("book", entity);
    }

    @Override
    public Book findByID(Long id) {
        return (Book) sessionFactory.getCurrentSession().byId(Book.class).load(id);
    }

    @Override
    public List<Book> findAll() {
        return (List<Book>) sessionFactory.getCurrentSession().createCriteria(Book.class).list();
    }

    @Override
    public List<Book> findByName(String bookName) {
        return (List<Book>) sessionFactory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("bookName", bookName)).list();
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Book.class, "book");
        criteria.createAlias("book.authors", "author");
        criteria.add(Restrictions.eq("author.authorLastName", authorName));
        return (List<Book>) criteria.list();
    }

    @Override
    public List<Book> findByCategory(String categoryName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Book.class, "book");
        criteria.createAlias("book.category", "category");
        criteria.add(Restrictions.eq("category.categoryName", categoryName));
        return (List<Book>) criteria.list();
    }
}
