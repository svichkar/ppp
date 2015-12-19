package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class Book {
    private int bookId;
    private String name;
    private int cellId;
    private int categoryId;

    public Book(int bookId, String name, int cellId, int categoryId) {
        this.bookId = bookId;
        this.name = name;
        this.cellId = cellId;
        this.categoryId = categoryId;
    }

    public Book(String name, int cellId, int categoryId) {
        this.name = name;
        this.cellId = cellId;
        this.categoryId = categoryId;
    }
}
