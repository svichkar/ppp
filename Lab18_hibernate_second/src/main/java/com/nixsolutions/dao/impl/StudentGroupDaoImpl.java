package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.entity.StudentGroup;
import com.nixsolutions.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StudentGroupDaoImpl implements StudentGroupDao {

	public StudentGroupDaoImpl() {
	}

	public void create(StudentGroup studentGroup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(studentGroup);
		transaction.commit();
	}

	public void update(StudentGroup studentGroup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(studentGroup);
		transaction.commit();
	}

	public void delete(StudentGroup studentGroup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(studentGroup);
		transaction.commit();
	}

	public StudentGroup getByStudentGroupId(int studentGroupId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		StudentGroup studentGroup = (StudentGroup) session.get(StudentGroup.class, studentGroupId);
		transaction.commit();
		return studentGroup;
	}

	public StudentGroup getByStudentGroupName(String studentGroupName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		StudentGroup studentGroup = (StudentGroup) session.createCriteria(StudentGroup.class)
				.add(Restrictions.eq("studentGroupName", studentGroupName)).uniqueResult();
		transaction.commit();
		return studentGroup;
	}

	@SuppressWarnings("unchecked")
	public List<StudentGroup> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<StudentGroup> toReturn = session.createCriteria(StudentGroup.class).list();
		transaction.commit();
		return toReturn;
	}
}