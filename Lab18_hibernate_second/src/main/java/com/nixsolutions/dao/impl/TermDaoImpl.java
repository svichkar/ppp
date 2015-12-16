package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Term;
import com.nixsolutions.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TermDaoImpl implements TermDao {
	//private static Logger LOG = LogManager.getLogger(TermDaoImpl.class.getName());

	public TermDaoImpl() {
	}

	public void create(Term term) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(term);
		transaction.commit();
	}

	public void update(Term term) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(term);
		transaction.commit();
	}

	public void delete(Term term) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(term);
		transaction.commit();
	}

	public Term getByTermId(int termId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Term term = (Term) session.get(Term.class, termId);
		transaction.commit();
		return term;
	}

	public Term getByTermAlias(String termName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Term.class);
		c.add(Restrictions.eq("alias", termName));
		Term term = (Term) c.uniqueResult();
		transaction.commit();
		return term;
	}

	@SuppressWarnings("unchecked")
	public List<Term> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Term> list = session.createCriteria(Term.class).list();
		transaction.commit();
		return list;
	}
}
