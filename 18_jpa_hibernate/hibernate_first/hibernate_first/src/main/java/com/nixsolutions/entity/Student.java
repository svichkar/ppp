package com.nixsolutions.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "first_name", length = 256, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 256, nullable = false)
    private String lastName;
    @Column(name = "date_birthday", nullable = false)
    private Date dateBirthday;
    @Column(name = "date_entry", nullable = false)
    private Date dateEntry;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "term_id")
    private Term term;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    public Student() {
    }

    public Integer getStudentId() {
	return studentId;
    }

    public void setStudentId(Integer studentId) {
	this.studentId = studentId;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Date getDateBirthday() {
	return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
	this.dateBirthday = dateBirthday;
    }

    public Date getDateEntry() {
	return dateEntry;
    }

    public void setDateEntry(Date dateEntry) {
	this.dateEntry = dateEntry;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
