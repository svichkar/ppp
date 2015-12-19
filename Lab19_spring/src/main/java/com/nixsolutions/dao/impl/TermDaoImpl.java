package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Term;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TermDaoImpl implements TermDao {
	@Autowired
	private SessionFactory sessionFactory;

	public TermDaoImpl() {
	}

	public void create(Term term) {
		sessionFactory.getCurrentSession().save(term);
	}

	public void update(Term term) {
		sessionFactory.getCurrentSession().saveOrUpdate(term);
	}

	public void delete(Term term) {
		sessionFactory.getCurrentSession().delete(term);
	}

	public Term getByTermId(int termId) {
		return (Term) sessionFactory.getCurrentSession().get(Term.class, termId);
	}

	public Term getByTermAlias(String termName) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Term.class);
		c.add(Restrictions.eq("alias", termName));
		return (Term) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Term> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Term.class).list();
	}
}
