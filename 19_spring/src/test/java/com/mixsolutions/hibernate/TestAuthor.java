package com.mixsolutions.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.nixsolutions.hibernate.dao.AuthorDAO;

import com.nixsolutions.hibernate.dao.BookDAO;
import com.nixsolutions.hibernate.entity.Author;

import com.nixsolutions.hibernate.entity.Book;
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

import java.util.ArrayList;
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
    @Autowired
    private BookDAO bookDAO;

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Author/Init.xml")
    @ExpectedDatabase("classpath:Author/Create.xml")
    public void testCreate() throws Exception {
        Author author = new Author();
        author.setAuthorFirstName("test");
        author.setAuthorLastName("test");
        author.setBooks(bookDAO.findAll());
        authorDAO.create(author);
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Author/Init.xml")
    @ExpectedDatabase("classpath:Author/Update.xml")
    public void testUpdate() throws Exception {
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
    public void testDelete() throws Exception {
        Author author = authorDAO.findByID(1L);
        authorDAO.delete(author);
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Author/Init.xml")
    public void testFindById() throws Exception {
        Author author = authorDAO.findByID(1L);
        Assert.assertEquals(new Long(1), author.getAuthorId());
        Assert.assertEquals("Aleksandr", author.getAuthorFirstName());
        Assert.assertEquals("Pushkin", author.getAuthorLastName());
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Author/Init.xml")
    public void testFindAll() throws Exception {
        List<Author> authors = authorDAO.findAll();
        Assert.assertEquals(1, authors.size());
        Assert.assertEquals(new Long(1), authors.get(0).getAuthorId());
        Assert.assertEquals("Aleksandr", authors.get(0).getAuthorFirstName());
        Assert.assertEquals("Pushkin", authors.get(0).getAuthorLastName());
    }
}
