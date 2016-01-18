package com.nixsolutions.studentgrade.entity;

import java.sql.Date;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Student {

    private Long studentId;
    private String firstName;
    private String lastName;
    private Long groupId;
    private Date admissionDate;
    private Long statusId;
    private Long termId;

    public Student() {
    }

    public Student(String firstName, String lastName, Long groupId, Date admissionDate, Long statusId, Long termId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupId = groupId;
        this.admissionDate = admissionDate;
        this.statusId = statusId;
        this.termId = termId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public boolean isEmpty() {

        if (this.studentId == null
                && this.firstName == null
                && this.lastName == null
                && this.groupId == null
                && this.admissionDate == null
                && this.statusId == null
                && this.termId == null) {
            return true;
        } else {
            return false;
        }
    }
}
