package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class Author {
    private int authorId;
    private String firstName;
    private String lastName;

    public Author(int authorId, String firstName, String lastName) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
