package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.dao.TermDAO;

@Repository("termDao")
public class TermDAOImpl implements TermDAO {

	@Autowired
    SessionFactory sessionFactory;

	@Override
	public void createTerm(Term term) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(term);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateTerm(Term term) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(term);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteTerm(Term term) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(term);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Term findTermById(Long termId) {
		Session session = sessionFactory.getCurrentSession();
		Term term = null;
		try {
			term = (Term) session.get(Term.class, termId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return term;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Term> findAllTerms() {
		Session session = sessionFactory.getCurrentSession();
		List<Term> terms = new ArrayList<Term>();
		try {
			terms = session.createCriteria(Term.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return terms;
	}

}
