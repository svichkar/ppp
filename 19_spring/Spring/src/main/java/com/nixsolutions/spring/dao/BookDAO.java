package com.nixsolutions.spring.dao;

import com.nixsolutions.spring.entity.Book;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
interface BookDAO extends GenericDAO<Book> {
    List<Book> findByName(String bookName);
    List<Book> findByAuthor(String authorName);
    List<Book> findByCategory(String categoryName);
}
