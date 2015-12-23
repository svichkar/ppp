package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class AuthorBook {
    private Integer id;
    private int authorId;
    private int bookId;

    public AuthorBook(Integer id, int authorId, int bookId) {
        this.id = id;
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public AuthorBook(Integer authorId, int bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
