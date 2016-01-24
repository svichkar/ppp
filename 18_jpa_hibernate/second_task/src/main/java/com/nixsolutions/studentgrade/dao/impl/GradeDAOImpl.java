package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.dao.GradeDAO;
import com.nixsolutions.studentgrade.util.HibernateUtil;

public class GradeDAOImpl implements GradeDAO {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createGrade(Grade grade) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(grade);
		transaction.commit();
	}

	@Override
	public void updateGrade(Grade grade) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(grade);
		transaction.commit();
	}

	@Override
	public void deleteGrade(Grade grade) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(grade);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
		} 
	}

	@Override
	public Grade findGradeById(Long gradeId) {
		Session session = sessionFactory.getCurrentSession();
		Grade grade = null;
		Transaction transaction = session.beginTransaction();
		grade = (Grade) session.get(Grade.class, gradeId);
		transaction.commit();
		return grade;
	}

	@Override
	public List<Grade> findAllGrades() {
		Session session = sessionFactory.getCurrentSession();
		List<Grade> grades = new ArrayList<Grade>();
		Transaction transaction = session.beginTransaction();
		grades = session.createCriteria(Grade.class).list();
		transaction.commit();
		return grades;
	}
}
