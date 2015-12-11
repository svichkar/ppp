package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Term;

public interface TermDao {

    public Boolean create(Term term);

    public Boolean update(Term term);

    public Boolean delete(Term term);

    public Term getByTermId(Integer termId);

    public Term getTermByAlias(String alias);

    public List<Term> getAll();
}
