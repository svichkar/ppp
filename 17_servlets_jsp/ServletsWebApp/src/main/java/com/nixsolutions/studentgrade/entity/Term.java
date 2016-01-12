package com.nixsolutions.studentgrade.entity;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Term {

    private Long termId;
    private String termName;

    public Term() {
    }

    public Term(String termName) {
        this.termName = termName;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }
}