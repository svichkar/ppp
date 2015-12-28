package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.AuthorBookDAO;
import com.nixsolutions.library.entity.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;

import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import java.util.List;

/**
 * Created by kozlovskij on 12/24/2015.
 */

public class TestAuthorBook extends DBUnitConfig {
    private AuthorBook authorBook;

    public TestAuthorBook(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/Author_book/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        authorBook = new AuthorBook(1, 2);
        AuthorBookDAO authorBookDAO = daoFactory.getAuthorBookDAO();
        authorBookDAO.create(authorBook);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author_book/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author_book"),
                expected.getTableMetaData("author_book").getColumns());
        Assertion.assertEquals(expected.getTable("author_book"), filteredTable);
    }

    public void testUpdate() throws Exception {
        authorBook = new AuthorBook(1, 2, 1);
        AuthorBookDAO authorBookDAO = daoFactory.getAuthorBookDAO();
        authorBookDAO.update(authorBook);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author_book/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author_book"),
                expected.getTableMetaData("author_book").getColumns());
        Assertion.assertEquals(expected.getTable("author_book"), filteredTable);
    }

    public void testDelete() throws Exception {
        authorBook = new AuthorBook(1, 1, 1);
        AuthorBookDAO authorBookDAO = daoFactory.getAuthorBookDAO();
        authorBookDAO.delete(authorBook);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author_book/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author_book"),
                expected.getTableMetaData("author_book").getColumns());
        Assertion.assertEquals(expected.getTable("author_book"), filteredTable);
    }

    public void testFindById() throws Exception {
        authorBook = new AuthorBook(1, 1, 1);
        AuthorBookDAO authorBookDAO = daoFactory.getAuthorBookDAO();
        AuthorBook actualAuthorBook;
        actualAuthorBook = authorBookDAO.findByID(authorBook.getId());

        Assert.assertEquals(authorBook.getId(), actualAuthorBook.getId());
        Assert.assertEquals(authorBook.getAuthorId(), actualAuthorBook.getAuthorId());
        Assert.assertEquals(authorBook.getBookId(), actualAuthorBook.getBookId());
    }

    public void testFindAll() throws Exception {
        AuthorBookDAO authorBookDAO = daoFactory.getAuthorBookDAO();
        List<AuthorBook> authorBooksList;
        authorBooksList = authorBookDAO.findAll();

        Assert.assertEquals(2, authorBooksList.size());

        Assert.assertEquals(new Integer(1), authorBooksList.get(0).getId());
        Assert.assertEquals(new Integer(1), authorBooksList.get(0).getAuthorId());
        Assert.assertEquals(new Integer(1), authorBooksList.get(0).getBookId());

        Assert.assertEquals(new Integer(2), authorBooksList.get(1).getId());
        Assert.assertEquals(new Integer(2), authorBooksList.get(1).getAuthorId());
        Assert.assertEquals(new Integer(2), authorBooksList.get(1).getBookId());
    }
}
