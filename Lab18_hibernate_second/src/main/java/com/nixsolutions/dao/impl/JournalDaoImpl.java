package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.entity.Journal;
import com.nixsolutions.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class JournalDaoImpl implements JournalDao {

	public JournalDaoImpl() {
	}

	public void create(Journal journal) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(journal);
		transaction.commit();
	}

	public void update(Journal journal) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(journal);
		transaction.commit();
	}

	public void delete(Journal journal) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(journal);
		transaction.commit();
	}

	public Journal getByJournalById(int journalId) {
		Journal journal = new Journal();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		journal = (Journal) session.get(Journal.class, journalId);
		transaction.commit();
		return journal;
	}

	@SuppressWarnings("unchecked")
	public List<Journal> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Journal> toReturn = session.createCriteria(Journal.class).list();
		transaction.commit();
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	public List<Journal> getJournalByStudentId(int studentId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Journal.class).add(Restrictions.eq("student.studentId", studentId));
		List<Journal> toReturn = c.list();
		transaction.commit();
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	public List<Journal> getJournalBySubjectId(int subjectId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Journal.class).add(Restrictions.eq("subject.subjectId", subjectId));
		List<Journal> toReturn = c.list();
		transaction.commit();
		return toReturn;
	}
}