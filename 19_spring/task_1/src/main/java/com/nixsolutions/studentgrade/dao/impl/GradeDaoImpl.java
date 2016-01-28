package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.model.Grade;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public class GradeDaoImpl implements GradeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Grade grade) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(grade);
        transaction.commit();
    }

    @Override
    public void update(Grade grade) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(grade);
        transaction.commit();
    }

    @Override
    public void delete(Grade grade) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(grade);
        transaction.commit();
    }

    @Override
    public List<Grade> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Grade> list = session.createCriteria(Grade.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public Grade findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Grade grade = (Grade) session.get(Grade.class, id);
        transaction.commit();
        return grade;
    }

    @Override
    public Grade findByName(String gradeName) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Grade.class);
        criteria.add(Restrictions.eq("gradeName", gradeName)).uniqueResult();
        List<Grade> grades = criteria.list();
        transaction.commit();
        return grades.isEmpty() ? null : grades.get(0);
    }
}
