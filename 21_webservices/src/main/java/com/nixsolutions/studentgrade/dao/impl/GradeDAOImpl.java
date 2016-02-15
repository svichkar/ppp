package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.dao.GradeDAO;

@Repository("gradeDao")
public class GradeDAOImpl implements GradeDAO {

    @Autowired
    SessionFactory sessionFactory;

	@Override
	public void createGrade(Grade grade) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(grade);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateGrade(Grade grade) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(grade);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteGrade(Grade grade) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(grade);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Grade findGradeById(Long gradeId) {
		Session session = sessionFactory.getCurrentSession();
		Grade grade = null;
		try {
			grade = (Grade) session.get(Grade.class, gradeId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return grade;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> findAllGrades() {
		Session session = sessionFactory.getCurrentSession();
		List<Grade> grades = new ArrayList<Grade>();
		try {
			grades = session.createCriteria(Grade.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return grades;
	}
}
