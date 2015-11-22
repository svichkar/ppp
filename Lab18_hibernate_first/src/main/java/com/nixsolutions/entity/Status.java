package com.nixsolutions.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Status implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "status_id")
    private int statusId;
    @Column(name = "status_name", length = 256, nullable = false)
    private String statusName;

    public Status() {
    }

    public int getId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
