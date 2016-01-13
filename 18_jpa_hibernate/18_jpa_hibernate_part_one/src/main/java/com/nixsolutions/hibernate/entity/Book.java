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
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "NAME", nullable = false)
    private String bookName;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "CELL_ID", referencedColumnName = "CELL_ID")
    private Cell cell;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    private Category category;

    @ManyToMany
    @JoinTable(name = "AUTHOR_BOOK",
            joinColumns = { @JoinColumn(name = "BOOK_ID")},
            inverseJoinColumns = { @JoinColumn(name = "AUTHOR_ID")})
    private List<Author> authors;

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
}
