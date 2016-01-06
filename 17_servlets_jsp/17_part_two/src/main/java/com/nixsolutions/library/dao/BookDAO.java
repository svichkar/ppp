package com.nixsolutions.library.dao;

import com.nixsolutions.library.entity.Book;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public interface BookDAO extends GenericDAO<Book> {
    List<Book> findByName (String name);
    List<Book> findByAuthor (String author);
    List<Book> findByCategory (String category);
}
