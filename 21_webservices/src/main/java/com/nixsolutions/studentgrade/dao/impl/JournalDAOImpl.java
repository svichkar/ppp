package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.dao.JournalDAO;

@Repository("journalDao")
public class JournalDAOImpl implements JournalDAO {

	@Autowired
    SessionFactory sessionFactory;

	@Override
	public void createJournal(Journal journal) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(journal);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateJournal(Journal journal) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(journal);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteJournal(Journal journal) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(journal);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Journal findJournalById(Long journalId) {
		Session session = sessionFactory.getCurrentSession();
		Journal journal = null;
		try {
			journal = (Journal) session.get(Journal.class, journalId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return journal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Journal> findAllJournals() {
		Session session = sessionFactory.getCurrentSession();
		List<Journal> journals = new ArrayList<Journal>();
		try {
			journals = session.createCriteria(Journal.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return journals;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Journal> findJournalsByStudentIdAndTermId(Long studentId, Long termId) {
		Session session = sessionFactory.getCurrentSession();
		List<Journal> journals = new ArrayList<Journal>();
		try {
			journals = session.createCriteria(Journal.class, "journal").createAlias("journal.subject", "subject")
					.createAlias("subject.term", "term").add(Restrictions.eq("term.termId", termId))
					.add(Restrictions.eq("student.studentId", studentId)).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return journals;
	}

	@Override
	public Long findGPAByStudentIdAndTermId(Long studentId, Long termId) {
		Session session = sessionFactory.getCurrentSession();
		Long gpa;
		try {
		Query query = session.createQuery(
				"select avg(journal.grade.gradeId) FROM Journal journal where journal.student.studentId=? and journal.subject.term.termId=?");
		gpa = Long.valueOf(Math.round(
				Double.valueOf(query.setParameter(0, studentId).setParameter(1, termId).uniqueResult().toString())));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return gpa;
	}

}
