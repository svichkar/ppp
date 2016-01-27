package com.nixsolutions.hibernate.dao;

import com.nixsolutions.hibernate.entity.Ticket;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public interface TicketDAO extends GenericDAO<Ticket> {
    List<Ticket> findOverdueTicket ();
}
