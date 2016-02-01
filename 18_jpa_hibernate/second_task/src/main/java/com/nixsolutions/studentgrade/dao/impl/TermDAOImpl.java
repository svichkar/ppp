package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.util.HibernateUtil;

public class TermDAOImpl implements TermDAO {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createTerm(Term term) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(term);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateTerm(Term term) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(term);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteTerm(Term term) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(term);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Term findTermById(Long termId) {
		Session session = sessionFactory.getCurrentSession();
		Term term = null;
		Transaction transaction = session.beginTransaction();
		try {
			term = (Term) session.get(Term.class, termId);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		return term;
	}

	@Override
	public List<Term> findAllTerms() {
		Session session = sessionFactory.getCurrentSession();
		List<Term> terms = new ArrayList<Term>();
		Transaction transaction = session.beginTransaction();
		try {
			terms = session.createCriteria(Term.class).list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		return terms;
	}

}
