package com.nixsolutions.studentgrade.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grade_id")
	private Long gradeId;
	@Column(name="grade_name",  nullable = false)
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

}
