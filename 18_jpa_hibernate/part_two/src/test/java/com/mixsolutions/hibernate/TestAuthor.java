package com.mixsolutions.hibernate;

import com.mixsolutions.hibernate.config.DBUnitConfig;
import com.nixsolutions.hibernate.dao.AuthorDAO;

import com.nixsolutions.hibernate.entity.Author;
import com.nixsolutions.hibernate.util.HibernateUtil;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.ext.h2.H2DataTypeFactory;
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
        author = new Author();
        author.setAuthorFirstName("Aleksandr");
        author.setAuthorLastName("Pushkin");
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        authorDAO.create(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"), filteredTable);
    }

    public void testUpdate() throws Exception {
        author = new Author();
        author.setAuthorId(1L);
        author.setAuthorFirstName("Aleksandr");
        author.setAuthorLastName("Pushkin");
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        authorDAO.update(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"), filteredTable);
    }

    public void testDelete() throws Exception {
        author = new Author();
        author.setAuthorId(1L);
        author.setAuthorFirstName("Oleg");
        author.setAuthorLastName("Kril");
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        authorDAO.delete(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"), filteredTable);
    }

    public void testFindById() throws Exception {
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        author = authorDAO.findByID(1L);
        Assert.assertEquals(new Long(1), author.getAuthorId());
        Assert.assertEquals("Oleg", author.getAuthorFirstName());
        Assert.assertEquals("Kril", author.getAuthorLastName());
    }

    public void testFindAll() throws Exception {
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        List<Author> authorList;
        authorList = authorDAO.findAll();

        Assert.assertEquals(2, authorList.size());

        Assert.assertEquals(new Long(1), authorList.get(0).getAuthorId());
        Assert.assertEquals("Oleg", authorList.get(0).getAuthorFirstName());
        Assert.assertEquals("Kril", authorList.get(0).getAuthorLastName());

        Assert.assertEquals(new Long(2), authorList.get(1).getAuthorId());
        Assert.assertEquals("Alex", authorList.get(1).getAuthorFirstName());
        Assert.assertEquals("Barchuk", authorList.get(1).getAuthorLastName());
    }
}
