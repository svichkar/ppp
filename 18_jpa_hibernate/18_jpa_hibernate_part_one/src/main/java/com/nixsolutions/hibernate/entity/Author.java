package com.nixsolutions.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kozlovskij on 1/13/2016.
 */
@Entity
public class Author implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String authorFirstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String authorLastName;

    @ManyToMany
    @JoinTable(name = "AUTHOR_BOOK",
            joinColumns = { @JoinColumn(name = "AUTHOR_ID")},
            inverseJoinColumns = { @JoinColumn(name = "BOOK_ID")})
    private List<Book> books;

    @Transient
    public String authorFullName (){
        return authorFirstName + " " + authorLastName;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
