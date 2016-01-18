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
}
