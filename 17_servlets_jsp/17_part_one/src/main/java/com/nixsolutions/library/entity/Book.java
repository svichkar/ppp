package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class Book {
    private Integer bookId;
    private String name;
    private Integer cellId;
    private Integer categoryId;

    public Book(Integer bookId, String name, Integer cellId, Integer categoryId) {
        this.bookId = bookId;
        this.name = name;
        this.cellId = cellId;
        this.categoryId = categoryId;
    }

    public Book(String name, Integer cellId, Integer categoryId) {
        this.name = name;
        this.cellId = cellId;
        this.categoryId = categoryId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public Integer getCellId() {
        return cellId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
