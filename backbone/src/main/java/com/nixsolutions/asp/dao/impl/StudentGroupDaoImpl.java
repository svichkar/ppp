package com.nixsolutions.asp.dao.impl;

import com.nixsolutions.asp.dao.StudentGroupDao;
import com.nixsolutions.asp.entity.StudentGroup;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentGroupDaoImpl implements StudentGroupDao {

	@Autowired
	private SessionFactory sessionFactory;

	public StudentGroupDaoImpl() {
	}

	public void create(StudentGroup studentGroup) {
		sessionFactory.getCurrentSession().save(studentGroup);
	}

	public void update(StudentGroup studentGroup) {
		sessionFactory.getCurrentSession().saveOrUpdate(studentGroup);
	}

	public void delete(StudentGroup studentGroup) {
		sessionFactory.getCurrentSession().delete(studentGroup);
	}

	public StudentGroup getByStudentGroupId(int studentGroupId) {
		return (StudentGroup) sessionFactory.getCurrentSession().get(StudentGroup.class, studentGroupId);
	}

	public StudentGroup getByStudentGroupName(String studentGroupName) {
		return (StudentGroup) sessionFactory.getCurrentSession().createCriteria(StudentGroup.class)
				.add(Restrictions.eq("studentGroupName", studentGroupName)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<StudentGroup> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(StudentGroup.class).list();
	}
}