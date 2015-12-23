package com.nixsolutions.library.app;

import com.nixsolutions.library.dao.impl.AuthorBookDaoImpl;
import com.nixsolutions.library.entity.AuthorBook;


/**
 * Created by kozlovskij on 12/22/2015.
 */
public class Testttt {
    public static void main(String[] args) {
        AuthorBookDaoImpl temp = new AuthorBookDaoImpl();
        AuthorBook author = new AuthorBook(52, 1);
        author = temp.create(author);
        System.out.println(author.getId());
        System.out.println(author.getAuthorId());
        System.out.println(author.getBookId());
    }
}
