package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.model.Grade;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@Repository
public class GradeDaoImpl implements GradeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Grade grade) {

        sessionFactory.getCurrentSession().save(grade);
    }

    @Override
    public void update(Grade grade) {

        sessionFactory.getCurrentSession().saveOrUpdate(grade);
     }

    @Override
    public void delete(Grade grade) {

       sessionFactory.getCurrentSession().delete(grade);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Grade> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<Grade> list = session.createCriteria(Grade.class).list();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Grade findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Grade grade = (Grade) session.get(Grade.class, id);
        return grade;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Grade findByName(String gradeName) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Grade.class);
        criteria.add(Restrictions.eq("gradeName", gradeName));
        Grade grade = (Grade) criteria.uniqueResult();
        return grade;
    }
}
