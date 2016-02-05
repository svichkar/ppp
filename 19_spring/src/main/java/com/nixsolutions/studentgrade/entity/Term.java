package com.nixsolutions.studentgrade.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Term implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "term_id")
	private Long termId;
	@Column(name = "term_name", nullable = false)
	private String termName;

	public Term() {
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
