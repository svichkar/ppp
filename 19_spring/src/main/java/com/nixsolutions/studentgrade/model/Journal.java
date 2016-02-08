package com.nixsolutions.studentgrade.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * Created by svichkar on 12/18/2015.
 */
@Entity
@Table(name = "journal")
public class Journal {

    @Id
    @Column(name = "journal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name  = "student_id", nullable = false)
    @Cascade(CascadeType.DETACH)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name  = "subject_id", nullable = false)
    @Cascade(CascadeType.DETACH)
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name  = "grade_id")
    @Cascade(CascadeType.DETACH)
    private Grade grade;

    public Journal() {
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journal journal = (Journal) o;

        if (!journalId.equals(journal.journalId)) return false;
        if (!student.equals(journal.student)) return false;
        if (!subject.equals(journal.subject)) return false;
        return grade.equals(journal.grade);

    }

    @Override
    public int hashCode() {
        int result = journalId.hashCode();
        result = 31 * result + student.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + grade.hashCode();
        return result;
    }
}
