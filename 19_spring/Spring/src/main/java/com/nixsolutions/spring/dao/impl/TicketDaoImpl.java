package com.nixsolutions.spring.dao.impl;

import com.nixsolutions.spring.dao.TicketDAO;
import com.nixsolutions.spring.entity.Ticket;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Ticket entity) {
        try {
            sessionFactory.getCurrentSession().save("ticket", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Ticket entity) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate("ticket", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Ticket entity) {
        try {
            sessionFactory.getCurrentSession().delete("ticket", entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket findByID(Long id) {
        Ticket entity = null;;
        try {
            entity = (Ticket) sessionFactory.getCurrentSession().byId(Ticket.class).load(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> list = null;
        try {
            list = sessionFactory.getCurrentSession().createCriteria(Ticket.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Ticket> findOverdueTicket() {
        List<Ticket> list = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(Ticket.class);
            criteria.add(Restrictions.isNull("returnDate"));
            criteria.add(Restrictions.le("expiredDate", new Timestamp(System.currentTimeMillis())));
            list = criteria.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
