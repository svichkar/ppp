package com.nixsolutions.hibernate.dao;

import com.nixsolutions.hibernate.entity.Book;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public interface BookDAO extends GenericDAO<Book> {
    List<Book> findByName(String bookName);
    List<Book> findByAuthor(String authorName);
    List<Book> findByCategory(String categoryName);
}
