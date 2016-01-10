package com.nixsolutions.library.dao;

import com.nixsolutions.library.entity.Ticket;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public interface TicketDAO extends GenericDAO<Ticket> {
    public List<Ticket> findByBookID(Integer id);
}
