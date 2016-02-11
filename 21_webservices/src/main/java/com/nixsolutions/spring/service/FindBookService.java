package com.nixsolutions.spring.service;

import com.nixsolutions.spring.dao.BookDAO;
import com.nixsolutions.spring.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kozlovskij on 2/2/2016.
 */

@Service
@Transactional
public class FindBookService {
    @Autowired
    private BookDAO bookDAO;

    public List<Book> find(String searchCriteria, String searchWord) {
        List<Book> books = null;
        switch (searchCriteria) {
            case "all":
                books = bookDAO.findAll();
                break;
            case "name":
                books = bookDAO.findByName(searchWord);
                break;
            case "author":
                books = bookDAO.findByAuthor(searchWord);
                break;
            case "category":
                books = bookDAO.findByCategory(searchWord);
                break;
            default:
                break;
        }
        return books;
    }
}
