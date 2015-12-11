package com.nixsolutions.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Term;
import com.nixsolutions.service.TermBo;

@Service
public class TermBoImpl implements TermBo {

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
