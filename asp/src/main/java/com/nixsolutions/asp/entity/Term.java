package com.nixsolutions.asp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Term implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "term_id")
    private int termId;
    @Column(name = "alias", length = 256, nullable = false)
    private String alias;

    public Term() {
    }

    public int getTermId() {
        return termId;
    }
    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
