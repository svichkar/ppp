package com.nixsolutions.studentgrade.entity;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Subject {

    private Long subjectId;
    private String subjectName;
    private Long termId;

    public Subject() {
    }

    public Subject(String subjectName, Long termId) {
        this.subjectName = subjectName;
        this.termId = termId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public boolean isEmpty() {
        if (this.subjectId == null && this.subjectName == null && this.termId == null) {
            return true;
        } else {
            return false;
        }
    }
}
