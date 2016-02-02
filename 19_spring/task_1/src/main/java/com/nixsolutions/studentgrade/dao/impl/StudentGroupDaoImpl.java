package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentGroupDao;
import com.nixsolutions.studentgrade.model.StudentGroup;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by konstantin on 1/28/2016.
 */
@Repository
public class StudentGroupDaoImpl implements StudentGroupDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(StudentGroup group) {

        sessionFactory.getCurrentSession().save(group);
    }

    @Override
    public void update(StudentGroup group) {

        sessionFactory.getCurrentSession().saveOrUpdate(group);
    }

    @Override
    public void delete(StudentGroup group) {

        sessionFactory.getCurrentSession().delete(group);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<StudentGroup> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<StudentGroup> list = session.createCriteria(StudentGroup.class).list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public StudentGroup findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        StudentGroup group = (StudentGroup) session.get(StudentGroup.class, id);
        return group;
    }

    @Override
    @SuppressWarnings("unchecked")
    public StudentGroup findByName(String groupName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(StudentGroup.class);
        criteria.add(Restrictions.eq("groupName", groupName)).uniqueResult();
        StudentGroup group = (StudentGroup) criteria.uniqueResult();
        return group;
    }
}
