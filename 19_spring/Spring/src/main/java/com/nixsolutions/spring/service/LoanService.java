package com.nixsolutions.spring.service;

import com.nixsolutions.spring.dao.BookDAO;
import com.nixsolutions.spring.dao.ClientDAO;
import com.nixsolutions.spring.dao.TicketDAO;
import com.nixsolutions.spring.entity.Book;
import com.nixsolutions.spring.entity.Client;
import com.nixsolutions.spring.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozlovskij on 2/2/2016.
 */
@Service
public class LoanService {
    @Autowired
    ClientDAO clientDAO;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    TicketDAO ticketDAO;

    public List<Client> clients () {
        return clientDAO.findAll();
    }
    public List<Book> checkedBooks (Long [] checkedBooks) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i <checkedBooks.length ; i++) {
            books.add(bookDAO.findByID(checkedBooks[i]));
        }
        return books;
    }
    public void createTicket (Long [] checkedBooks, Long clientId) {
        for (int i = 0; i <checkedBooks.length ; i++) {
            Ticket ticket = new Ticket();
            ticket.setBook(bookDAO.findByID(checkedBooks[i]));
            ticket.setClient(clientDAO.findByID(clientId));
            ticket.setRentDate(new Timestamp(System.currentTimeMillis()));
            ticket.setExpiredDate(new Timestamp(System.currentTimeMillis() + 345600000L));
            ticketDAO.create(ticket);
        }
    }
}
