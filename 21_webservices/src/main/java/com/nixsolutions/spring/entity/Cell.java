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
public class Cell implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cell_id")
    private Long cellId;

    @Column(name = "name", nullable = false)
    private String cellName;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cell_id", referencedColumnName = "cell_id")
    private List<Book> books;

    public Cell() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (cellId != null ? !cellId.equals(cell.cellId) : cell.cellId != null) return false;
        if (cellName != null ? !cellName.equals(cell.cellName) : cell.cellName != null) return false;
        return books != null ? books.equals(cell.books) : cell.books == null;

    }

    @Override
    public int hashCode() {
        int result = cellId != null ? cellId.hashCode() : 0;
        result = 31 * result + (cellName != null ? cellName.hashCode() : 0);
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }
}
