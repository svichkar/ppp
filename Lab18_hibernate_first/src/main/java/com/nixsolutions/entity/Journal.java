package com.nixsolutions.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Journal implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
	private int journalId;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
	private Student student;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
	private Subject subject;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "rate_id")
	private Rate rate;

	public Journal(){
	}

	public int getId() {
		return journalId;
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

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}	
}
