package com.nixsolutions.spring.service;

import com.nixsolutions.spring.dao.TicketDAO;
import com.nixsolutions.spring.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kozlovskij on 2/2/2016.
 */
@Service
public class MainService {
    @Autowired
    private TicketDAO ticketDAO;

    public List<Ticket> findOverdueTicket() {
        return ticketDAO.findOverdueTicket();
    }
}
