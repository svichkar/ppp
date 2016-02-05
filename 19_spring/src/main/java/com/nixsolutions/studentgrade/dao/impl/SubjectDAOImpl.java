package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.dao.SubjectDAO;

@Repository("subjectDao")
@Transactional
public class SubjectDAOImpl implements SubjectDAO {

	@Autowired
    SessionFactory sessionFactory;

	@Override
	public void createSubject(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(subject);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateSubject(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(subject);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteSubject(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(subject);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Subject findSubjectById(Long subjectId) {
		Session session = sessionFactory.getCurrentSession();
		Subject subject = null;
		try {
			subject = (Subject) session.get(Subject.class, subjectId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return subject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> findAllSubjects() {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			subjects = session.createCriteria(Subject.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return subjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> findSubjectsByName(String subjectName) {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			subjects = session.createCriteria(Subject.class).add(Restrictions.eq("subjectName", subjectName)).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return subjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> findSubjectsByTermId(Long termId) {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			subjects = session.createCriteria(Subject.class).add(Restrictions.eq("term.termId", termId)).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return subjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> findSubjectByNameAndTermId(String subjectName, Long termId) {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			subjects = session.createCriteria(Subject.class).add(Restrictions.eq("subjectName", subjectName))
					.add(Restrictions.eq("term.termId", termId)).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return subjects;
	}

}
