package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Term;

public interface TermDAO {

	public void createTerm(Term term);

	public void updateTerm(Term term);

	public void deleteTerm(Term term);

	public Term findTermById(Long termId);

	public List<Term> findAllTerms();
	
}
