package com.nixsolutions.library.app;

import com.nixsolutions.library.dao.impl.AuthorBookDaoImpl;
import com.nixsolutions.library.entity.AuthorBook;



/**
 * Created by kozlovskij on 12/22/2015.
 */
public class Testttt {
    public static void main(String[] args) {
        AuthorBookDaoImpl temp = new AuthorBookDaoImpl();
        AuthorBook ab = temp.findByID(10);
        if (ab != null) {
            System.out.println(ab.getId() + "|" + ab.getAuthorId() + "|" + ab.getBookId());
        } else {
            System.out.println("not found");
        }
    }
}
