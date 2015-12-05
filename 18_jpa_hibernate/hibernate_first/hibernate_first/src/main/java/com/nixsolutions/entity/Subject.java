package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Subject implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private int subjectId;
    @Column(name = "subject_name", length = 256, nullable = false)
    private String subjectName;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "term_id")
    private Term term;

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Subject() {
    }

    public int getSubjectId() {
	return subjectId;
    }

    public void setSubjectId(int subjectId) {
	this.subjectId = subjectId;
    }

    public String getSubjectName() {
	return subjectName;
    }

    public void setSubjectName(String subjectName) {
	this.subjectName = subjectName;
    }
}
