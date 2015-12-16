package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Term;

public class TermDaoImpl extends AbstractDaoImpl implements TermDao {

    @Override
    public Boolean create(Term term) {
	return super.insert(term);
    }

    @Override
    public Boolean update(Term term) {
	return super.update(term);
    }

    @Override
    public Boolean delete(Term term) {
	return super.delete(term);
    }

    @Override
    public Term getByTermId(Integer termId) {
	Term t = new Term();
	t.setTermId(termId);
	List<Term> termList = super.findBySeveralFields(t, new String[]{"termId"});
	if (termList.size() > 0) {
	    return termList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public Term getTermByAlias(String alias) {
	Term t = new Term();
	t.setTermAlias(alias);
	List<Term> termList = super.findBySeveralFields(t, new String[]{"termAlias"});
	if (termList.size() > 0) {
	    return termList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Term> getAll() {
	return super.findBySeveralFields(new Term(), null);
    }

}
