package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Status implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "status_name", length = 256, nullable = false)
    private String statusName;

    public Status() {
    }

    public Integer getStatusId() {
	return statusId;
    }

    public void setStatusId(Integer statusId) {
	this.statusId = statusId;
    }

    public String getStatusName() {
	return statusName;
    }

    public void setStatusName(String statusName) {
	this.statusName = statusName;
    }
}
