package com.nixsolutions.asp.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.asp.dao.TermDao;
import com.nixsolutions.asp.entity.Term;
import com.nixsolutions.asp.service.TermService;

@Service
public class TermServiceImpl implements TermService {

	@Autowired
	private TermDao termDao;
	
	@Override
	public void create(Term term) {
		termDao.create(term);
	}

	@Override
	public void update(Term term) {
		termDao.update(term);
	}

	@Override
	public void delete(Term term) {
		termDao.delete(term);
	}

	@Override
	public Term getByTermId(int termId) {
		return termDao.getByTermId(termId);
	}

	@Override
	public Term getByTermAlias(String alias) {
		return termDao.getByTermAlias(alias);
	}

	@Override
	public List<Term> getAll() {
		return termDao.getAll();
	}

}
