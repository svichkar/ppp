package com.nixsolutions.studentgrade.entity;

import java.sql.Date;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Student {

    private int studentId;
    private String firstName;
    private String lastName;
    private int groupId;
    private Date admissionDate;
    private int statusId;
    private int termId;

    /**
     * Default constructor
     */
    public Student() {
    }

    /**
     * Constructor with all fields
     * @param studentId
     * @param firstName
     * @param lastName
     * @param groupId
     * @param admissionDate
     * @param statusId
     * @param termId
     */
    public Student(int studentId, String firstName, String lastName, int groupId, Date admissionDate, int statusId, int termId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupId = groupId;
        this.admissionDate = admissionDate;
        this.statusId = statusId;
        this.termId = termId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }
}
