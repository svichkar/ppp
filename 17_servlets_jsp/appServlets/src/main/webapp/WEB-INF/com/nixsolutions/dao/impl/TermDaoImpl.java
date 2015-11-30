package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Term;

public class TermDaoImpl extends AbstractDaoImpl implements TermDao {
    private static String TABLE_NAME = "term";

    @Override
    public Boolean create(Term term) {
	return super.insert(term, Term.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean update(Term term) {
	return super.update(term, Term.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean delete(Term term) {
	return super.delete("term_id", term.getTermId(), TABLE_NAME);
    }

    @Override
    public Term getByTermId(int termId) {
	List<Term> termList = super.find("term_id", String.valueOf(termId), Term.getMap(), Term.class);
	if (termList.size() > 0) {
	    return termList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public Term getTermByAlias(String alias) {
	List<Term> termList = super.find("term_alias", String.valueOf(alias), Term.getMap(), Term.class);
	if (termList.size() > 0) {
	    return termList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Term> getAll() {
	return super.find(null, null, Term.getMap(), Term.class);
    }

}
