package com.nixsolutions.studentgrade.bean;

import java.io.Serializable;

/**
 * Created by konstantin on 1/17/2016.
 */
public class SubjectBean implements Serializable {

    private Long id;
    private String name;
    private Long termId;
    private String term;

    public SubjectBean() {
    }

    public SubjectBean(Long id, String name, Long termId, String term) {
        this.id = id;
        this.name = name;
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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }


    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }
}
