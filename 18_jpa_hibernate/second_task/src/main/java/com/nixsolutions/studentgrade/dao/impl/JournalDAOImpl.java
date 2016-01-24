package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.dao.JournalDAO;
import com.nixsolutions.studentgrade.util.HibernateUtil;

public class JournalDAOImpl implements JournalDAO {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createJournal(Journal journal) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(journal);
		transaction.commit();
	}

	@Override
	public void updateJournal(Journal journal) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(journal);
		transaction.commit();
	}

	@Override
	public void deleteJournal(Journal journal) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(journal);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
		} 
	}

	@Override
	public Journal findJournalById(Long journalId) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Journal journal = null;
		journal = (Journal) session.get(Journal.class, journalId);
		transaction.commit();
		return journal;
	}

	@Override
	public List<Journal> findAllJournals() {
		Session session = sessionFactory.getCurrentSession();
		List<Journal> journals = new ArrayList<Journal>();
		Transaction transaction = session.beginTransaction();
		journals = session.createCriteria(Journal.class).list();
		transaction.commit();
		return journals;
	}

	@Override
	public List<Journal> findJournalsByStudentIdAndTermId(Long studentId, Long termId) {
		Session session = sessionFactory.getCurrentSession();
		List<Journal> journals = new ArrayList<Journal>();
		Transaction transaction = session.beginTransaction();
		journals = session.createCriteria(Journal.class, "journal").createAlias("journal.subject", "subject")
				.createAlias("subject.term", "term").add(Restrictions.eq("term.termId", termId))
				.add(Restrictions.eq("student.studentId", studentId)).list();
		transaction.commit();
		return journals;
	}

	@Override
	public Long findGPAByStudentIdAndTermId(Long studentId, Long termId) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(
				"select avg(journal.grade.gradeId) FROM Journal journal where journal.student.studentId=? and journal.subject.term.termId=?");
		Long gpa = Long.valueOf(Math.round(
				Double.valueOf(query.setParameter(0, studentId).setParameter(1, termId).uniqueResult().toString())));
		transaction.commit();
		return gpa;
	}

}
