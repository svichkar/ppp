package com.nixsolutions.library.app;

import com.nixsolutions.library.dao.*;
import com.nixsolutions.library.dao.impl.DaoFactoryImpl;
import com.nixsolutions.library.entity.*;

import java.sql.Date;

/**
 * Created by Serko on 26.12.2015.
 */
public class Demo {
    public static void main(String[] args) {
        DaoFactory daoFactory = new DaoFactoryImpl();

        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        AuthorBookDAO authorBookDAO = daoFactory.getAuthorBookDAO();
        BookDAO bookDAO = daoFactory.getBookDAO();
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        CellDAO cellDAO = daoFactory.getCellDAO();
        ClientDAO clientDAO = daoFactory.getClientDAO();
        TicketDAO ticketDAO = daoFactory.getTicketDAO();

        Author author = new Author("Alina", "Gannenko");
        AuthorBook authorBook = new AuthorBook(1, 1);
        Book book = new Book("Stories", 1, 1);
        Category category = new Category("comedy");
        Cell cell = new Cell("A");
        Client client = new Client("Alina", "Gannenko", "777-77-77", "super@mail.com");
        java.sql.Date date = new Date(1451150988584L);
        Ticket ticket = new Ticket(1, 1, date, date, date);

        author = authorDAO.create(author);
        category = categoryDAO.create(category);
        cell = cellDAO.create(cell);
        book = bookDAO.create(book);
        authorBook = authorBookDAO.create(authorBook);
        client = clientDAO.create(client);
        ticket = ticketDAO.create(ticket);

        author.setLastName("Smith");
        authorDAO.update(author);

        ticketDAO.delete(ticket);

        System.out.println(bookDAO.findByID(1).getName());

        System.out.println(clientDAO.findAll().size());
    }
}
