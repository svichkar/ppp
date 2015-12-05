package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Journal implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "journal_id")
    private int journalId;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @Column(name = "rate", length = 256, nullable = false)
    private String rate;

    public Journal() {
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

    public int getJournalId() {
	return journalId;
    }

    public void setJournalId(int journalId) {
	this.journalId = journalId;
    }

    public String getRate() {
	return rate;
    }

    public void setRate(String rate) {
	this.rate = rate;
    }
}
