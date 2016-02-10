package com.nixsolutions.spring.dao;

import com.nixsolutions.spring.entity.Ticket;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
interface TicketDAO extends GenericDAO<Ticket> {
    List<Ticket> findOverdueTicket();
}
