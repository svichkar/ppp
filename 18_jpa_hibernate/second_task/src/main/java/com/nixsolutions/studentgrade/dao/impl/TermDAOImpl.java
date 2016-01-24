package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
		session.save(term);
		transaction.commit();
	}

	@Override
	public void updateTerm(Term term) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(term);
		transaction.commit();
	}

	@Override
	public void deleteTerm(Term term) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(term);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
		} 
	}

	@Override
	public Term findTermById(Long termId) {
		Session session = sessionFactory.getCurrentSession();
		Term term = null;
		Transaction transaction = session.beginTransaction();
		term = (Term) session.get(Term.class, termId);
		transaction.commit();
		return term;
	}

	@Override
	public List<Term> findAllTerms() {
		Session session = sessionFactory.getCurrentSession();
		List<Term> terms = new ArrayList<Term>();
		Transaction transaction = session.beginTransaction();
		terms = session.createCriteria(Term.class).list();
		transaction.commit();
		return terms;
	}

	@Override
	public List<Term> findTermsByStudentId(Long studentId) {
		Session session = sessionFactory.getCurrentSession();
		List<Term> terms = new ArrayList<Term>();		
		Transaction transaction = session.beginTransaction();        
		Query query = session
				.createQuery("select term.termId, term.termName FROM Student student join student.term term where student.studentId=?");
		query.setParameter(0, studentId);
		List<Object[]> resultList = query.list();
        for(Object[] t : resultList){
        	Term term = new Term();
        	term.setTermId(Long.valueOf(t[0].toString()));
        	term.setTermName(t[1].toString());
        	terms.add(term);
        }
		transaction.commit();
		return terms;
	}
}
