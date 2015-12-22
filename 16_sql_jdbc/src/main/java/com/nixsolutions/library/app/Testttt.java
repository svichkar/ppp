package com.nixsolutions.library.app;

import com.nixsolutions.library.dao.impl.AuthorDaoImpl;
import com.nixsolutions.library.entity.Author;
import com.sun.glass.ui.SystemClipboard;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class Testttt {
    public static void main(String[] args) {
        AuthorDaoImpl temp = new AuthorDaoImpl();
        for (int i = 0; i <20 ; i++) {
            temp.delete(new Author("Vasya","Alekseev"));
        }
    }
}
