package com.nixsolutions.studentgrade.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((termId == null) ? 0 : termId.hashCode());
		result = prime * result + ((termName == null) ? 0 : termName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Term other = (Term) obj;
		if (termId == null) {
			if (other.termId != null)
				return false;
		} else if (!termId.equals(other.termId))
			return false;
		if (termName == null) {
			if (other.termName != null)
				return false;
		} else if (!termName.equals(other.termName))
			return false;
		return true;
	}
	
	

}
