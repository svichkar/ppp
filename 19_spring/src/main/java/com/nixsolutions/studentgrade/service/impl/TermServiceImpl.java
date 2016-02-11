package com.nixsolutions.studentgrade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.service.TermService;

@Service
@Transactional
public class TermServiceImpl implements TermService {

	@Autowired
	TermDAO termDao;

	@Override
	public Long createTerm(String termName) {
		Term newTerm = new Term();
		newTerm.setTermName(termName);
		termDao.createTerm(newTerm);
		return newTerm.getTermId();
	}

	@Override
	public void updateTerm(Long termId, String termName) {
		Term updatedTerm = termDao.findTermById(termId);
		updatedTerm.setTermName(termName);
		termDao.updateTerm(updatedTerm);
	}

	@Override
	public void deleteTerm(Long termId) {
		termDao.deleteTerm(termDao.findTermById(termId));
	}

	@Override
	public Term findTermById(Long termId) {
		return termDao.findTermById(termId);
	}

	@Override
	public List<Term> findAllTerms() {
		return termDao.findAllTerms();
	}

}
