package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.entity.Student;
//import com.nixsolutions.entity.StudentGroup;
import com.nixsolutions.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

	public StudentDaoImpl() {
	}

	public void create(Student student) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(student);
		transaction.commit();
	}

	public void update(Student student) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(student);
		transaction.commit();
	}

	public void delete(Student student) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(student);
		transaction.commit();
	}

	public Student getByStudentId(int studentId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student student = (Student) session.get(Student.class, studentId);
		transaction.commit();
		return student;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getByStudentsByName(String firstName, String lastName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Student.class);
		c.add(Restrictions.eq("firstName", firstName));
		c.add(Restrictions.eq("lastName", lastName));
		List<Student> toReturn = c.list();
		transaction.commit();
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getStudentsByGroupId(int studentGroupId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Student.class);
		c.add(Restrictions.eq("studentGroup.studentGroupId", studentGroupId));
		List<Student> toReturn = c.list();
		transaction.commit();
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Student> toReturn = session.createCriteria(Student.class).list();
		transaction.commit();
		return toReturn;
	}
}