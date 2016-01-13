package com.nixsolutions.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kozlovskij on 1/13/2016.
 */
@Entity
public class Cell implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CELL_ID")
    private Long cellId;

    @Column(name = "NAME", nullable = false)
    private String cellName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CELL_ID", referencedColumnName = "CELL_ID")
    private List<Book> books;

    public Long getCellId() {
        return cellId;
    }

    public void setCellId(Long cellId) {
        this.cellId = cellId;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
