package com.nixsolutions.studentgrade.entity;

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
}
