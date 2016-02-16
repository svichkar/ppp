package com.nixsolutions.asp.dao.impl;

import com.nixsolutions.asp.dao.SubjectDao;
import com.nixsolutions.asp.entity.Subject;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SubjectDaoImpl implements SubjectDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SubjectDaoImpl() {
	}

	public void create(Subject subject) {
		sessionFactory.getCurrentSession().save(subject);
	}

	public void update(Subject subject) {
		sessionFactory.getCurrentSession().saveOrUpdate(subject);
	}

	public void delete(Subject subject) {
		sessionFactory.getCurrentSession().delete(subject);
	}

	public Subject getBySubjectId(int subjectId) {
		return (Subject) sessionFactory.getCurrentSession().get(Subject.class, subjectId);
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Subject.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getBySubjectName(String name) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Subject.class);
		c.add(Restrictions.eq("name", name));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getSubjectsByTermId(int termId) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Subject.class);
		c.add(Restrictions.eq("term.termId", termId));
		return c.list();
	}
}