package com.nixsolutions.spring.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kozlovskij on 1/13/2016.
 */
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "name", nullable = false)
    private String bookName;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "cell_id", referencedColumnName = "cell_id")
    private Cell cell;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private List<Ticket> tickets;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "author_book",
            joinColumns = { @JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "author_id")})
    private List<Author> authors;

    @Transient
    public Ticket getCurrentOpenTicket () {
        Ticket ticket = null;
        for (int i = 0; i <this.tickets.size() ; i++) {
            ticket = this.tickets.get(i);
            if (ticket.isReturned().equals(false)){
                return ticket;
            } else {
                ticket = null;
            }
        }
        return ticket;
    }

    public Book() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != null ? !bookId.equals(book.bookId) : book.bookId != null) return false;
        if (bookName != null ? !bookName.equals(book.bookName) : book.bookName != null) return false;
        if (cell != null ? !cell.equals(book.cell) : book.cell != null) return false;
        if (category != null ? !category.equals(book.category) : book.category != null) return false;
        if (tickets != null ? !tickets.equals(book.tickets) : book.tickets != null) return false;
        return authors != null ? authors.equals(book.authors) : book.authors == null;

    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (cell != null ? cell.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }
}
