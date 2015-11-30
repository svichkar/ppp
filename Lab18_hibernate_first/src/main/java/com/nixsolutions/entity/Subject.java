package com.nixsolutions.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Subject implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private int subjectId;
    @Column(name = "name", length = 256, nullable = false)
    private String name;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "term_id")
    private Term term;

    public Subject() {
    }

    public int getId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

}
