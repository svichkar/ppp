package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.TicketDAO;
import com.nixsolutions.library.entity.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import java.sql.Date;
import java.util.List;

/**
 * Created by Serko on 26.12.2015.
 */
public class TestTicket extends DBUnitConfig {
    private Ticket ticket;

    public TestTicket(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/Ticket/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        java.sql.Date date = new Date(1451150988584L);
        ticket = new Ticket(1, 2, date, date, date);
        TicketDAO ticketDAO = daoFactory.getTicketDAO();
        ticketDAO.create(ticket);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Ticket/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("rent_journal"),
                expected.getTableMetaData("rent_journal").getColumns());
        Assertion.assertEquals(expected.getTable("rent_journal"), filteredTable);
    }

    public void testUpdate() throws Exception {
        java.sql.Date date = new Date(1451150988584L);
        ticket = new Ticket(1, 1, 2, date, date, date);
        TicketDAO ticketDAO = daoFactory.getTicketDAO();
        ticketDAO.update(ticket);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Ticket/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("rent_journal"),
                expected.getTableMetaData("rent_journal").getColumns());
        Assertion.assertEquals(expected.getTable("rent_journal"), filteredTable);
    }

    public void testDelete() throws Exception {
        java.sql.Date date = new Date(1451150988584L);
        ticket = new Ticket(1, 1, 1, date, date, date);
        TicketDAO ticketDAO = daoFactory.getTicketDAO();
        ticketDAO.delete(ticket);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Ticket/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("rent_journal"),
                expected.getTableMetaData("rent_journal").getColumns());
        Assertion.assertEquals(expected.getTable("rent_journal"), filteredTable);
    }

    public void testFindById() throws Exception {
        java.sql.Date date = new Date(1451150988584L);
        ticket = new Ticket(1, 1, 1, date, date, date);
        TicketDAO ticketDAO = daoFactory.getTicketDAO();
        Ticket actualTicket;
        actualTicket = ticketDAO.findByID(ticket.getTicketId());

        Assert.assertEquals(ticket.getTicketId(), actualTicket.getTicketId());
        Assert.assertEquals(ticket.getClientId(), actualTicket.getClientId());
        Assert.assertEquals(ticket.getBookId(), actualTicket.getBookId());
        Assert.assertEquals(ticket.getRentDate().toString(), actualTicket.getRentDate().toString());
        Assert.assertEquals(ticket.getExpiredDate().toString(), actualTicket.getExpiredDate().toString());
        Assert.assertEquals(ticket.getReturnDate().toString(), actualTicket.getReturnDate().toString());
    }

    public void testFindAll() throws Exception {
        TicketDAO ticketDAO = daoFactory.getTicketDAO();
        List<Ticket> ticketList;
        ticketList = ticketDAO.findAll();

        Assert.assertEquals(2, ticketList.size());

        Assert.assertEquals(new Integer(1), ticketList.get(0).getTicketId());
        Assert.assertEquals(new Integer(1), ticketList.get(0).getBookId());
        Assert.assertEquals(new Integer(1), ticketList.get(0).getClientId());
        Assert.assertEquals("2015-12-26", ticketList.get(0).getRentDate().toString());
        Assert.assertEquals("2015-12-26", ticketList.get(0).getExpiredDate().toString());
        Assert.assertEquals("2015-12-26", ticketList.get(0).getReturnDate().toString());

        Assert.assertEquals(new Integer(2), ticketList.get(1).getTicketId());
        Assert.assertEquals(new Integer(2), ticketList.get(1).getBookId());
        Assert.assertEquals(new Integer(2), ticketList.get(1).getClientId());
        Assert.assertEquals("2015-12-26", ticketList.get(1).getRentDate().toString());
        Assert.assertEquals("2015-12-26", ticketList.get(1).getExpiredDate().toString());
        Assert.assertEquals("2015-12-26", ticketList.get(1).getReturnDate().toString());
    }
}
