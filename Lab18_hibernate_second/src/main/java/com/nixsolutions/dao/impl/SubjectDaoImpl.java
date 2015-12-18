package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.entity.Subject;
import com.nixsolutions.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SubjectDaoImpl implements SubjectDao {

	public SubjectDaoImpl() {
	}

	public void create(Subject subject) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(subject);
		transaction.commit();

	}

	public void update(Subject subject) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(subject);
		transaction.commit();

	}

	public void delete(Subject subject) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(subject);
		transaction.commit();

	}

	public Subject getBySubjectId(int subjectId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Subject subject = (Subject) session.get(Subject.class, subjectId);
		transaction.commit();
		return subject;
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Subject> toReturn = session.createCriteria(Subject.class).list();
		transaction.commit();
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getBySubjectName(String name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Subject.class);
		c.add(Restrictions.eq("name", name));
		List<Subject> toReturn = c.list();
		transaction.commit();
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getSubjectsByTermId(int termId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Subject.class);
		c.add(Restrictions.eq("term.termId", termId));
		List<Subject> toReturn = c.list();
		transaction.commit();
		return toReturn;
	}
}