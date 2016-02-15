package com.nixsolutions.studentgrade.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by svichkar on 12/18/2015.
 */
@Entity
@Table(name = "grade")
public class Grade implements Serializable {

    @Id
    @Column(name = "grade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @Column(name = "grade_name", nullable = false, unique = true, length = 256)
    private String gradeName;

    public Grade() {

    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        if (!gradeId.equals(grade.gradeId)) return false;
        return gradeName.equals(grade.gradeName);

    }

    @Override
    public int hashCode() {
        int result = gradeId.hashCode();
        result = 31 * result + gradeName.hashCode();
        return result;
    }
}
