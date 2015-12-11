package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Term implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "term_id")
    private Integer termId;
    @Column(name = "term_alias", length = 256, nullable = false)
    private String termAlias;

    public Term() {
    }

    public Integer getTermId() {
	return termId;
    }

    public void setTermId(Integer termId) {
	this.termId = termId;
    }

    public String getTermAlias() {
	return termAlias;
    }

    public void setTermAlias(String termAlias) {
	this.termAlias = termAlias;
    }
}
