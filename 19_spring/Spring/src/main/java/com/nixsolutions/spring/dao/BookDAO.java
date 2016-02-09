package com.nixsolutions.spring.dao;

import com.nixsolutions.spring.entity.Book;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public interface BookDAO extends GenericDAO<Book> {
    public List<Book> findByName(String bookName);
    public List<Book> findByAuthor(String authorName);
    public List<Book> findByCategory(String categoryName);
}
