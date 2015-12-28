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

    public Integer getId() {
        return id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
