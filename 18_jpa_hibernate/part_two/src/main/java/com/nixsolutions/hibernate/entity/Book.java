package com.nixsolutions.hibernate.entity;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private List<Ticket> tickets;

    @ManyToMany
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
            }
        }
        return ticket;
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
}
