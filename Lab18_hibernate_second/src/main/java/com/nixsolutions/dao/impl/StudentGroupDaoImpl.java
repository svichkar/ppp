package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.entity.StudentGroup;
import com.nixsolutions.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class StudentGroupDaoImpl implements StudentGroupDao {
	private final static Logger LOG = LogManager.getLogger(StudentGroupDaoImpl.class.getName());

	public StudentGroupDaoImpl() {
	}
	public void create(StudentGroup studentGroup) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(studentGroup);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
	}
	
	public void update(StudentGroup studentGroup) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(studentGroup);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
	}

	public void delete(StudentGroup studentGroup) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(studentGroup);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
	}

	public StudentGroup getByStudentGroupId(int studentGroupId) {
        StudentGroup studentGroup = new StudentGroup();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            studentGroup = (StudentGroup) session.get(StudentGroup.class, studentGroupId);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return  studentGroup;
	}

	public StudentGroup getByStudentGroupName(String studentGroupName) {
        StudentGroup studentGroup = new StudentGroup();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Criteria c = session.createCriteria(StudentGroup.class);
            c.add(Restrictions.eq("studentGroupName", studentGroupName));
            List list = c.list();
            studentGroup = (StudentGroup) list.get(0);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return studentGroup;
	}

	public List<StudentGroup> getAll() {
        List<StudentGroup> toReturn = new ArrayList<StudentGroup>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            toReturn.addAll(session.createCriteria(StudentGroup.class).list());
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex);
        }
        return toReturn;
	}
}