package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.AuthorDAO;
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
public class TestAuthor extends DBUnitConfig {
    private Author author;

    public TestAuthor(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/Author/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        author = new Author("Aleksandr", "Pushkin");
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        authorDAO.create(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"), filteredTable);
    }

    public void testUpdate() throws Exception {
        author = new Author(1, "Aleksandr", "Pushkin");
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        authorDAO.update(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"), filteredTable);
    }

    public void testDelete() throws Exception {
        author = new Author(1, "Oleg", "Kril");
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        authorDAO.delete(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"), filteredTable);
    }

    public void testFindById() throws Exception {
        author = new Author(1, "Oleg", "Kril");
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        Author actualAuthor;
        actualAuthor = authorDAO.findByID(author.getAuthorId());

        Assert.assertEquals(author.getAuthorId(), actualAuthor.getAuthorId());
        Assert.assertEquals(author.getFirstName(), actualAuthor.getFirstName());
        Assert.assertEquals(author.getLastName(), actualAuthor.getLastName());
    }

    public void testFindAll() throws Exception {
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        List<Author> authorList;
        authorList = authorDAO.findAll();

        Assert.assertEquals(2, authorList.size());

        Assert.assertEquals(new Integer(1), authorList.get(0).getAuthorId());
        Assert.assertEquals("Oleg", authorList.get(0).getFirstName());
        Assert.assertEquals("Kril", authorList.get(0).getLastName());

        Assert.assertEquals(new Integer(2), authorList.get(1).getAuthorId());
        Assert.assertEquals("Alex", authorList.get(1).getFirstName());
        Assert.assertEquals("Barchuk", authorList.get(1).getLastName());
    }
}
