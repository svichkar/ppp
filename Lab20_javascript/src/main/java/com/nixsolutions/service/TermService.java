package com.nixsolutions.service;

import com.nixsolutions.entity.Term;

import java.util.List;

public interface TermService {
	public void create(Term term);

	public void update(Term term);

	public void delete(Term term);

	public Term getByTermId(int termId);
	
	public Term getByTermAlias(String alias);

	public List<Term> getAll();
}
