package com.nixsolutions.studentgrade.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by svichkar on 12/18/2015.
 */
@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(name = "subject_name", nullable = false, unique = true, length = 256)
    private String subjectName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name  = "term_id")
    @Cascade(CascadeType.DETACH)
    private Term term;

    public Subject() {
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public boolean isEmpty() {
        boolean result = false;

        if(this.subjectId == null && this.subjectName == null && this.term == null)
            result = true;

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (!subjectId.equals(subject.subjectId)) return false;
        if (!subjectName.equals(subject.subjectName)) return false;
        return term.equals(subject.term);

    }

    @Override
    public int hashCode() {
        int result = subjectId.hashCode();
        result = 31 * result + subjectName.hashCode();
        result = 31 * result + term.hashCode();
        return result;
    }
}
