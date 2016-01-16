package com.nixsolutions.hibernate.dao.impl;

import com.nixsolutions.hibernate.dao.TicketDAO;
import com.nixsolutions.hibernate.entity.Ticket;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public class TicketDaoImpl implements TicketDAO {
    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public void create(Ticket entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
    }


    @Override
    public void update(Ticket entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    @Override
    public void delete(Ticket entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    @Override
    public Ticket findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Ticket ticket = null;
        Transaction transaction = session.beginTransaction();
        ticket = (Ticket) session.get(Ticket.class, id);
        transaction.commit();
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Ticket> list = null;
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(User.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public List<Ticket> findOverdueTicket() {
        Session session = sessionFactory.getCurrentSession();
        List<Ticket> list = null;
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(Ticket.class).add(Restrictions.isNull("returnDate")).list();
        transaction.commit();
        return list;
    }
}
