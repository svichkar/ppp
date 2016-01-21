package com.nixsolutions.studentgrade.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by svichkar on 12/18/2015.
 */
@Entity
@Table(name = "status")
public class Status implements Serializable {

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @Column(name = "status_name", nullable = false, unique = true, length = 256)
    private String statusName;

    public Status() {
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
