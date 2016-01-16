package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.CellDAO;
import com.nixsolutions.hibernate.entity.Cell;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class CellDaoImpl implements CellDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public void create(Cell entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }


    @Override
    public void update(Cell entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    @Override
    public void delete(Cell entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    @Override
    public Cell findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Cell cell = null;
        Transaction transaction = session.beginTransaction();
        cell = (Cell) session.get(Cell.class, id);
        transaction.commit();
        return cell;
    }

    @Override
    public List<Cell> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Cell> list = null;
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(Cell.class).list();
        transaction.commit();
        return list;
    }
}
