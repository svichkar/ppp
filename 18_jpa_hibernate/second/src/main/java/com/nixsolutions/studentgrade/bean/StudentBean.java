package com.nixsolutions.studentgrade.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by svichkar on 1/18/2016.
 */
public class StudentBean implements Serializable {

    private Long id;
    private String name;
    private String lastName;
    private Date date;
    private Long groupId;
    private String group;
    private Long statusId;
    private String status;
    private Long termId;
    private String term;

    public StudentBean() {
    }

    public StudentBean(Long id, String name, String lastName, Date date, Long groupId, String group, Long statusId, String status, Long termId, String term) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.date = date;
        this.groupId = groupId;
        this.group = group;
        this.statusId = statusId;
        this.status = status;
        this.termId = termId;
        this.term = term;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
