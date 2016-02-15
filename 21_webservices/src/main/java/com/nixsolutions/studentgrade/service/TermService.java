package com.nixsolutions.studentgrade.service;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Term;

public interface TermService {

	public Long createTerm(String termName);

	public void updateTerm(Long termId, String termName);

	public void deleteTerm(Long termId);

	public Term findTermById(Long termId);

	public List<Term> findAllTerms();
	
}
