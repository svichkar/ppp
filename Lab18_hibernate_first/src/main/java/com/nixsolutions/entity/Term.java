package com.nixsolutions.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Term implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "term_id")
    private int termId;
    @Column(name = "alias", length = 256, nullable = false)
    private String alias;

    public Term() {
    }

    public int getId() {
        return termId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
