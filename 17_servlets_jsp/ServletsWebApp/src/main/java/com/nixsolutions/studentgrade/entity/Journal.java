package com.nixsolutions.studentgrade.entity;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Journal {

    private Long journalId;
    private Long studentId;
    private Long subjectId;
    private Long gradeId;

    public Journal() {
    }

    public Journal(Long studentId, Long subjectId, Long gradeId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.gradeId = gradeId;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
}
