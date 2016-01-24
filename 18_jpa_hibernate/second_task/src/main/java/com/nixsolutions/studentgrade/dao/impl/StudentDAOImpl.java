package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.util.HibernateUtil;

public class StudentDAOImpl implements StudentDAO {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(student);
		transaction.commit();
	}

	@Override
	public void updateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(student);
		transaction.commit();
	}

	@Override
	public void deleteStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(student);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
		} 
	}

	@Override
	public Student findStudentById(Long studentId) {
		Session session = sessionFactory.getCurrentSession();
		Student student = null;
		Transaction transaction = session.beginTransaction();
		student = (Student) session.get(Student.class, studentId);
		transaction.commit();
		return student;
	}

	@Override
	public List<Student> findAllStudents() {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = new ArrayList<Student>();
		Transaction transaction = session.beginTransaction();
		students = session.createCriteria(Student.class).list();
		transaction.commit();
		return students;
	}

	@Override
	public List<Student> findStudentsByLastNameAndGroupId(String lastName, Long groupId) {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = new ArrayList<Student>();
		Transaction transaction = session.beginTransaction();
		students = session.createCriteria(Student.class).add(Restrictions.eq("lastName", lastName))
				.add(Restrictions.eq("group.groupId", groupId)).list();
		transaction.commit();
		return students;
	}

	@Override
	public List<Student> findStudentsByLastName(String lastName) {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = new ArrayList<Student>();
		Transaction transaction = session.beginTransaction();
		students = session.createCriteria(Student.class).add(Restrictions.eq("lastName", lastName)).list();
		transaction.commit();
		return students;
	}

	@Override
	public List<Student> findStudentsByGroupId(Long groupId) {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = new ArrayList<Student>();
		Transaction transaction = session.beginTransaction();
		students = session.createCriteria(Student.class).add(Restrictions.eq("group.groupId", groupId)).list();
		transaction.commit();
		return students;
	}

}
