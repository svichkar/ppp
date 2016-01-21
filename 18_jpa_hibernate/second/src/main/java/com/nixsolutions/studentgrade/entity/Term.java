package com.nixsolutions.studentgrade.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by svichkar on 12/18/2015.
 */
@Entity
@Table(name = "term")
public class Term implements Serializable {

    @Id
    @Column(name = "term_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @Column(name = "term_name", nullable = false, unique = true, length = 256)
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