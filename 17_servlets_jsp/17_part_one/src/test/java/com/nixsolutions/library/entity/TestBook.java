package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.BookDAO;
import com.nixsolutions.library.entity.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Serko on 25.12.2015.
 */
public class TestBook extends DBUnitConfig {
    private Book book;

    public TestBook(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/Book/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        book = new Book("History", 1, 2);
        BookDAO bookDAO = daoFactory.getBookDAO();
        bookDAO.create(book);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Book/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("book"),
                expected.getTableMetaData("book").getColumns());
        Assertion.assertEquals(expected.getTable("book"), filteredTable);
    }

    public void testUpdate() throws Exception {
        book = new Book(1, "Alphabet", 2, 1);
        BookDAO bookDAO = daoFactory.getBookDAO();
        bookDAO.update(book);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Book/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("book"),
                expected.getTableMetaData("book").getColumns());
        Assertion.assertEquals(expected.getTable("book"), filteredTable);
    }

    public void testDelete() throws Exception {
        book = new Book(1, "bard tales", 1, 1);
        BookDAO bookDAO = daoFactory.getBookDAO();
        bookDAO.delete(book);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Book/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("book"),
                expected.getTableMetaData("book").getColumns());
        Assertion.assertEquals(expected.getTable("book"), filteredTable);
    }

    public void testFindById() throws Exception {
        BookDAO bookDAO = daoFactory.getBookDAO();
        book = bookDAO.findByID(1);

        Assert.assertEquals(new Integer(1), book.getBookId());
        Assert.assertEquals("bard tales", book.getName());
        Assert.assertEquals(new Integer(1), book.getCellId());
        Assert.assertEquals(new Integer(1), book.getCategoryId());
    }

    public void testFindAll() throws Exception {
        BookDAO bookDAO = daoFactory.getBookDAO();
        List<Book> bookList;
        bookList = bookDAO.findAll();

        Assert.assertEquals(2, bookList.size());

        Assert.assertEquals(new Integer(1), bookList.get(0).getBookId());
        Assert.assertEquals("bard tales", bookList.get(0).getName());
        Assert.assertEquals(new Integer(1), bookList.get(0).getCellId());
        Assert.assertEquals(new Integer(1), bookList.get(0).getCategoryId());

        Assert.assertEquals(new Integer(2), bookList.get(1).getBookId());
        Assert.assertEquals("stories", bookList.get(1).getName());
        Assert.assertEquals(new Integer(2), bookList.get(1).getCellId());
        Assert.assertEquals(new Integer(2), bookList.get(1).getCategoryId());
    }
}
