package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.entity.Journal;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class JournalDaoImpl implements JournalDao {

	@Autowired
	private SessionFactory sessionFactory;

	public JournalDaoImpl() {
	}

	public void create(Journal journal) {
		sessionFactory.getCurrentSession().save(journal);
	}

	public void update(Journal journal) {
		sessionFactory.getCurrentSession().saveOrUpdate(journal);
	}

	public void delete(Journal journal) {
		sessionFactory.getCurrentSession().delete(journal);
	}

	public Journal getByJournalById(int journalId) {
		return (Journal) sessionFactory.getCurrentSession().get(Journal.class, journalId);
	}

	@SuppressWarnings("unchecked")
	public List<Journal> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Journal.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Journal> getJournalByStudentId(int studentId) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Journal.class)
				.add(Restrictions.eq("student.studentId", studentId));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Journal> getJournalBySubjectId(int subjectId) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Journal.class)
				.add(Restrictions.eq("subject.subjectId", subjectId));
		return c.list();
	}
}