package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Term;

public interface TermDao {

    public void create(String alias);

    public void update(Term term);

    public void delete(Term term);

    public Term getByTermId(int termId);

    public Term getTermByAlias(String alias);

    public List<Term> getAll();
}
