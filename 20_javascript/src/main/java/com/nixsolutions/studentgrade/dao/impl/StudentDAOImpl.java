package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.dao.StudentDAO;

@Repository("studentDao")
@Transactional
public class StudentDAOImpl implements StudentDAO {

	@Autowired
    SessionFactory sessionFactory;

	@Override
	public void createStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(student);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(student);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(student);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Student findStudentById(Long studentId) {
		Session session = sessionFactory.getCurrentSession();
		Student student = null;
		try {
			student = (Student) session.get(Student.class, studentId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findAllStudents() {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = new ArrayList<Student>();
		try {
			students = session.createCriteria(Student.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findStudentsByLastNameAndGroupId(String lastName, Long groupId) {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = new ArrayList<Student>();
		try {
			students = session.createCriteria(Student.class).add(Restrictions.eq("lastName", lastName))
					.add(Restrictions.eq("group.groupId", groupId)).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findStudentsByLastName(String lastName) {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = new ArrayList<Student>();
		try {
			students = session.createCriteria(Student.class).add(Restrictions.eq("lastName", lastName)).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findStudentsByGroupId(Long groupId) {
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = new ArrayList<Student>();
		try {
			students = session.createCriteria(Student.class).add(Restrictions.eq("group.groupId", groupId)).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return students;
	}

}
