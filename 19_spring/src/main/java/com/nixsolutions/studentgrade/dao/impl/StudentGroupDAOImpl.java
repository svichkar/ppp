package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.dao.StudentGroupDAO;

@Repository("groupDao")
@Transactional
public class StudentGroupDAOImpl implements StudentGroupDAO {

	@Autowired
    SessionFactory sessionFactory;

	@Override
	public void createStudentGroup(StudentGroup group) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(group);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateStudentGroup(StudentGroup group) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(group);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteStudentGroup(StudentGroup group) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(group);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public StudentGroup findStudentGroupById(Long groupId) {
		Session session = sessionFactory.getCurrentSession();
		StudentGroup group = null;
		try {
			group = (StudentGroup) session.get(StudentGroup.class, groupId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return group;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentGroup> findAllStudentGroups() {
		Session session = sessionFactory.getCurrentSession();
		List<StudentGroup> groups = new ArrayList<StudentGroup>();
		try {
			groups = session.createCriteria(StudentGroup.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return groups;
	}

}
