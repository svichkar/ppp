package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.dao.StudentGroupDAO;
import com.nixsolutions.studentgrade.util.HibernateUtil;

public class StudentGroupDAOImpl implements StudentGroupDAO {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createStudentGroup(StudentGroup group) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(group);
		transaction.commit();
	}

	@Override
	public void updateStudentGroup(StudentGroup group) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(group);
		transaction.commit();
	}

	@Override
	public void deleteStudentGroup(StudentGroup group) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(group);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
		} 
	}

	@Override
	public StudentGroup findStudentGroupById(Long groupId) {
		Session session = sessionFactory.getCurrentSession();
		StudentGroup group = null;
		Transaction transaction = session.beginTransaction();
		group = (StudentGroup) session.get(StudentGroup.class, groupId);
		transaction.commit();
		return group;
	}

	@Override
	public List<StudentGroup> findAllStudentGroups() {
		Session session = sessionFactory.getCurrentSession();
		List<StudentGroup> groups = new ArrayList<StudentGroup>();
		Transaction transaction = session.beginTransaction();
		groups = session.createCriteria(StudentGroup.class).list();
		transaction.commit();
		return groups;
	}

}
