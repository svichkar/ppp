package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentGroupDao;
import com.nixsolutions.studentgrade.model.StudentGroup;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by konstantin on 1/28/2016.
 */
@Repository
@Transactional
public class StudentGroupDaoImpl implements StudentGroupDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(StudentGroup group) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(group);
        transaction.commit();
    }

    @Override
    @Transactional
    public void update(StudentGroup group) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(group);
        transaction.commit();
    }

    @Override
    @Transactional
    public void delete(StudentGroup group) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(group);
        transaction.commit();
    }

    @Override
    public List<StudentGroup> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<StudentGroup> list = session.createCriteria(StudentGroup.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public StudentGroup findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        StudentGroup group = (StudentGroup) session.get(StudentGroup.class, id);
        transaction.commit();
        return group;
    }

    @Override
    public StudentGroup findByName(String group) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(StudentGroup.class);
        criteria.add(Restrictions.eq("groupName", group)).uniqueResult();
        List<StudentGroup> groupList = criteria.list();
        transaction.commit();
        return groupList.isEmpty() ? null : groupList.get(0);
    }
}
