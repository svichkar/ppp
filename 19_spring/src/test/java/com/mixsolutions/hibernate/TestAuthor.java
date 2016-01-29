package com.mixsolutions.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.nixsolutions.hibernate.dao.AuthorDAO;

import com.nixsolutions.hibernate.entity.Author;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;


/**
 * Created by kozlovskij on 12/24/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TestAuthor {
    @Autowired
    private AuthorDAO authorDAO;

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Author/Init.xml")
    @ExpectedDatabase("classpath:Author/Create.xml")
    public void testCreate () throws Exception {
        Author author = new Author();
        author.setAuthorFirstName("test");
        author.setAuthorLastName("test");
        authorDAO.create(author);
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Author/Init.xml")
    @ExpectedDatabase("classpath:Author/Update.xml")
    public void testUpdate () throws Exception {
        Author author = authorDAO.findByID(1L);
        author.setAuthorFirstName("test");
        author.setAuthorLastName("test");
        authorDAO.update(author);
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Author/Init.xml")
    @ExpectedDatabase("classpath:Author/Delete.xml")
    public void testDelete () throws Exception {
        Author author = authorDAO.findByID(1L);
        authorDAO.delete(author);
    }
    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Author/Init.xml")
    public void testFindById () throws Exception {
        Author author = authorDAO.findByID(1L);
        Assert.assertEquals(new Long(1),author.getAuthorId());
        Assert.assertEquals("Aleksandr",author.getAuthorFirstName());
        Assert.assertEquals("Pushkin",author.getAuthorLastName());
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Author/Init.xml")
    public void testFindAll () throws Exception {
        List<Author> authors = authorDAO.findAll();
        Assert.assertEquals(1,authors.size());
        Assert.assertEquals(new Long(1),authors.get(0).getAuthorId());
        Assert.assertEquals("Aleksandr",authors.get(0).getAuthorFirstName());
        Assert.assertEquals("Pushkin",authors.get(0).getAuthorLastName());
    }

   /* @Override
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
    }*/
}
