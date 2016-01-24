package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.util.HibernateUtil;

public class SubjectDAOImpl implements SubjectDAO {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Override
	public void createSubject(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(subject);
		transaction.commit();
	}

	@Override
	public void updateSubject(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(subject);
		transaction.commit();
	}

	@Override
	public void deleteSubject(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(subject);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
		} 
	}

	@Override
	public Subject findSubjectById(Long subjectId) {
		Session session = sessionFactory.getCurrentSession();
		Subject subject = null;
		Transaction transaction = session.beginTransaction();
		subject = (Subject) session.get(Subject.class, subjectId);
		transaction.commit();
		return subject;
	}

	@Override
	public List<Subject> findAllSubjects() {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjects = new ArrayList<Subject>();
		Transaction transaction = session.beginTransaction();
		subjects = session.createCriteria(Subject.class).list();
		transaction.commit();
		return subjects;
	}

	@Override
	public List<Subject> findSubjectsByName(String subjectName) {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjects = new ArrayList<Subject>();
		Transaction transaction = session.beginTransaction();
		subjects = session.createCriteria(Subject.class).add(Restrictions.eq("subjectName", subjectName)).list();
		transaction.commit();
		return subjects;
	}

	@Override
	public List<Subject> findSubjectsByTermId(Long termId) {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjects = new ArrayList<Subject>();
		Transaction transaction = session.beginTransaction();
		subjects = session.createCriteria(Subject.class).add(Restrictions.eq("term.termId", termId)).list();
		transaction.commit();
		return subjects;
	}

	@Override
	public List<Subject> findSubjectByNameAndTermId(String subjectName, Long termId) {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjects = new ArrayList<Subject>();
		Transaction transaction = session.beginTransaction();
		subjects = session.createCriteria(Subject.class).add(Restrictions.eq("subjectName", subjectName))
				.add(Restrictions.eq("term.termId", termId)).list();
		transaction.commit();
		return subjects;
	}

}
