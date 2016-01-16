package com.nixsolutions.hibernate;


import com.nixsolutions.hibernate.dao.DaoFactory;
import com.nixsolutions.hibernate.dao.TicketDAO;
import com.nixsolutions.hibernate.dao.impl.DaoFactoryImpl;
import com.nixsolutions.hibernate.entity.*;
import com.nixsolutions.hibernate.util.HibernateUtil;


import java.util.List;

/**
 * Created by kozlovskij on 1/13/2016.
 */
public class Main {
    public static void main(String[] args) {
        DaoFactory daoFactory = new DaoFactoryImpl();
        TicketDAO ticketDAO = daoFactory.getTicketDAO();
        List<Ticket> tickets = ticketDAO.findOverdueTicket();
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket =  tickets.get(i);
            System.out.println(ticket.getClient().clientFullName() + "_" + ticket.getBook().getBookName());
        }
        Role role = new Role();
        role.setRoleName("LIBRARIAN");
        daoFactory.getRoleDAO().create(role);
        HibernateUtil.closeSessionFactory();
    }
}
