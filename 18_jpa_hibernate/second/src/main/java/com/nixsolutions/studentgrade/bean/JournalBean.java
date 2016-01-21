package com.nixsolutions.studentgrade.bean;

import java.io.Serializable;

/**
 * Created by konstantin on 1/17/2016.
 */
public class JournalBean implements Serializable {

    private Long id;
    private String name;
    private String lastName;
    private Long groupId;
    private String group;
    private Long subjectId;
    private String subject;
    private Long gradeId;
    private String grade;

    public JournalBean(Long id, String name, String lastName, Long groupId, String group, Long subjectId, String subject, Long gradeId, String grade) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.groupId = groupId;
        this.group = group;
        this.subjectId = subjectId;
        this.subject = subject;
        this.gradeId = gradeId;
        this.grade = grade;
    }

    public JournalBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
