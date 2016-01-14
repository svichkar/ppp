package com.nixsolutions.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Journal implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "journal_id")
	private Long journalId;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="student_id", referencedColumnName = "student_id", nullable = false)
	private Student student;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="subject_id", referencedColumnName = "subject_id", nullable = false)
	private Subject subject;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="grade_id", referencedColumnName = "grade_id", nullable = false)
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
