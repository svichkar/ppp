package com.nixsolutions.library.bean;

import com.nixsolutions.library.entity.Author;
import com.nixsolutions.library.entity.Book;
import com.nixsolutions.library.entity.Category;
import com.nixsolutions.library.entity.Cell;
import com.nixsolutions.library.entity.Ticket;

import java.util.List;

/**
 * Created by Serko on 09.01.2016.
 */
public class BeanBook {
    private Book book;
    private Cell cell;
    private Category category;
    private List<Author> authors;
    private Ticket ticket;

    public com.nixsolutions.library.entity.Book getBook() {
        return book;
    }

    public void setBook(com.nixsolutions.library.entity.Book book) {
        this.book = book;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
