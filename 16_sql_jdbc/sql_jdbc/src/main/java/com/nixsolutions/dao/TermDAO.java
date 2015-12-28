package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Term;

public interface TermDAO {

	public Term createTerm(int termId, String termName);

	public void updateTerm(Term term);

	public void deleteTerm(Term term);

	public Term findTermById(int termId);

	public List<Term> findAllTerms();

}
