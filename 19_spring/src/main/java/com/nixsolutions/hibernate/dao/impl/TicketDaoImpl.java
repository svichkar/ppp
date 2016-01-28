package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.TicketDAO;
import com.nixsolutions.hibernate.entity.Ticket;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
@Repository("ticketDAO")
@Transactional
public class TicketDaoImpl implements TicketDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Ticket entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Ticket entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Ticket entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Ticket ticket = null;
        Transaction transaction = session.beginTransaction();
        try {
            ticket = (Ticket) session.get(Ticket.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Ticket> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            list = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Ticket> findOverdueTicket() {
        Session session = sessionFactory.getCurrentSession();
        List<Ticket> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Ticket.class);
            criteria.add(Restrictions.isNull("returnDate"));
            criteria.add(Restrictions.le("expiredDate", new Timestamp(System.currentTimeMillis())));
            list = criteria.list();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return list;
    }
}
