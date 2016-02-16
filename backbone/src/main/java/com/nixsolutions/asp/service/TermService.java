package com.nixsolutions.asp.service;

import com.nixsolutions.asp.entity.Term;

import java.util.List;
import java.util.Map;

public interface TermService {
	void create(Term term);

	void update(Term term);

	void delete(Term term);

	Term getByTermId(int termId);
	
	Term getByTermAlias(String alias);

	List<Term> getAll();
	
	Map<String, String> getTermMap();
}
