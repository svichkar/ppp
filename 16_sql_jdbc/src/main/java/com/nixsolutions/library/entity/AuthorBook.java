package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class AuthorBook {
    private int id;
    private int authorId;
    private int bookId;

    public AuthorBook(int id, int authorId, int bookId) {
        this.id = id;
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public AuthorBook(int authorId, int bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }
}
