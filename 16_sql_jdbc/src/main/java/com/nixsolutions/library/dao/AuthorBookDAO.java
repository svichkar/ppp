package com.nixsolutions.library.dao;

import com.nixsolutions.library.entity.AuthorBook;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public interface AuthorBookDAO extends GenericDAO<AuthorBook> {
    public List<AuthorBook> findByBookID(Integer id);
}
