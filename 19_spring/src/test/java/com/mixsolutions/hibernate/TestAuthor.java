package com.mixsolutions.hibernate;

import com.nixsolutions.hibernate.dao.AuthorDAO;

import com.nixsolutions.hibernate.entity.Author;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.util.List;


/**
 * Created by kozlovskij on 12/24/2015.
 */
public class TestAuthor extends DBTestCase {
    private JdbcDatabaseTester tester;
    @Autowired
    private AuthorDAO authorDAO;

    public TestAuthor(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:sqllab");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root");
    }
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().setColumnSensing(true).build(new FileInputStream("src/test/resources/Author/init.xml"));
    }

    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester("org.h2.Driver",
                "jdbc:h2:mem:sqllab", "root", "root");
        tester.setDataSet(this.getDataSet());
    }

    public void testCreate() throws Exception {
        Author author = new Author();
        author.setAuthorFirstName("Aleksandr");
        author.setAuthorLastName("Pushkin");
        authorDAO.create(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"), filteredTable);
    }

    public void testUpdate() throws Exception {
        Author author = new Author();
        author.setAuthorFirstName("Aleksandr");
        author.setAuthorLastName("Pushkin");
        authorDAO.create(author);
        author.setAuthorLastName("Gogol");
        authorDAO.update(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"), filteredTable);
    }

    public void testDelete() throws Exception {
        Author author = new Author();
        author.setAuthorFirstName("Aleksandr");
        author.setAuthorLastName("Pushkin");
        authorDAO.create(author);
        authorDAO.delete(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"), filteredTable);
    }

    public void testFindById() throws Exception {
        Author author = new Author();
        author.setAuthorFirstName("Aleksandr");
        author.setAuthorLastName("Pushkin");
        authorDAO.create(author);
        Long actualId = author.getAuthorId();
        author = authorDAO.findByID(author.getAuthorId());
        Assert.assertEquals(actualId, author.getAuthorId());
        Assert.assertEquals("Aleksandr", author.getAuthorFirstName());
        Assert.assertEquals("Pushkin", author.getAuthorLastName());
    }

    public void testFindAll() throws Exception {
        Author author = new Author();
        author.setAuthorFirstName("Aleksandr");
        author.setAuthorLastName("Pushkin");
        authorDAO.create(author);
        List<Author> authorList;
        authorList = authorDAO.findAll();

        Assert.assertEquals(1, authorList.size());

        Assert.assertEquals(new Long(1), author.getAuthorId());
        Assert.assertEquals("Aleksandr", author.getAuthorFirstName());
        Assert.assertEquals("Pushkin", author.getAuthorLastName());
    }
}
