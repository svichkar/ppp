package com.nixsolutions.spring.service;

import com.nixsolutions.spring.dao.AuthorDAO;
import com.nixsolutions.spring.dao.BookDAO;
import com.nixsolutions.spring.dao.CategoryDAO;
import com.nixsolutions.spring.dao.CellDAO;
import com.nixsolutions.spring.entity.Author;
import com.nixsolutions.spring.entity.Category;
import com.nixsolutions.spring.entity.Cell;
import com.nixsolutions.spring.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozlovskij on 2/2/2016.
 */
@Service
@Transactional
public class AddBookService {
    @Autowired
    private AuthorDAO authorDAO;
    @Autowired
    private CellDAO cellDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private BookDAO bookDAO;

    public List<Author> authors() {
        return authorDAO.findAll();
    }

    public List<Cell> cells() {
        return cellDAO.findAll();
    }

    public List<Category> categories() {
        return categoryDAO.findAll();
    }
    public void addBook (String bookName, Long categoryId, Long cellId, Long [] authorId, Integer bookQuantity) {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i <authorId.length ; i++) {
            authors.add(authorDAO.findByID(authorId[i]));
        }
        for (int i = 0; i <bookQuantity ; i++) {
            Book book = new Book();
            book.setBookName(bookName);
            book.setCategory(categoryDAO.findByID(categoryId));
            book.setCell(cellDAO.findByID(cellId));
            book.setAuthors(authors);
            bookDAO.create(book);
        }
    }
}
