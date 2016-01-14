package com.nixsolutions.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private Long subjectId;
	@Column(name="subject_name",  nullable = false)
	private String subjectName;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="term_id", referencedColumnName = "term_id", nullable = false)
	private Term term;

	public Subject() {
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

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
}
