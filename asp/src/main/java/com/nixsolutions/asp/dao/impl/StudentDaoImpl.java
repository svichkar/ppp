package com.nixsolutions.asp.dao.impl;

import com.nixsolutions.asp.dao.StudentDao;
import com.nixsolutions.asp.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public StudentDaoImpl() {
	}

	public void create(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}

	public void update(Student student) {
		sessionFactory.getCurrentSession().saveOrUpdate(student);
	}

	public void delete(Student student) {
		sessionFactory.getCurrentSession().delete(student);
	}

	public Student getByStudentId(int studentId) {
		return (Student) sessionFactory.getCurrentSession().get(Student.class, studentId);
	}

	@SuppressWarnings("unchecked")
	public List<Student> getByStudentsByName(String firstName, String lastName) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Student.class);
		c.add(Restrictions.eq("firstName", firstName));
		c.add(Restrictions.eq("lastName", lastName));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Student> getStudentsByGroupId(int studentGroupId) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Student.class);
		c.add(Restrictions.eq("studentGroup.studentGroupId", studentGroupId));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Student.class).list();
	}
}