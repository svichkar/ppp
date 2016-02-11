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
        sessionFactory.getCurrentSession().save("ticket", entity);
    }


    @Override
    public void update(Ticket entity) {
        sessionFactory.getCurrentSession().saveOrUpdate("ticket", entity);
    }

    @Override
    public void delete(Ticket entity) {
        sessionFactory.getCurrentSession().delete("ticket", entity);
    }

    @Override
    public Ticket findByID(Long id) {
        return (Ticket) sessionFactory.getCurrentSession().byId(Ticket.class).load(id);
    }

    @Override
    public List<Ticket> findAll() {
        return (List<Ticket>) sessionFactory.getCurrentSession().createCriteria(Ticket.class).list();
    }

    @Override
    public List<Ticket> findOverdueTicket() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Ticket.class);
        criteria.add(Restrictions.isNull("returnDate"));
        criteria.add(Restrictions.le("expiredDate", new Timestamp(System.currentTimeMillis())));
        return (List<Ticket>) criteria.list();
    }
}
