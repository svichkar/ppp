package com.nixsolutions.studentgrade.bean;

import java.io.Serializable;

/**
 * Created by svichkar on 1/18/2016.
 */
public class StudentDetailsBean implements Serializable {

    private Long studentId;
    private String subject;
    private String grade;

    public StudentDetailsBean() {
    }

    public StudentDetailsBean(String subject, String grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
