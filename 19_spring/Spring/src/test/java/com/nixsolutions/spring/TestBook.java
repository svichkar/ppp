package com.nixsolutions.spring;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.nixsolutions.spring.dao.BookDAO;
import com.nixsolutions.spring.dao.CategoryDAO;
import com.nixsolutions.spring.dao.CellDAO;
import com.nixsolutions.spring.entity.Book;
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
 * Created by kozlovskij on 1/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TestBook {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private CellDAO cellDAO;

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Book/Init.xml")
    @ExpectedDatabase("classpath:Book/Create.xml")
    public void testCreate() throws Exception {
        Book book = new Book();
        book.setCategory(categoryDAO.findByID(1L));
        book.setCell(cellDAO.findByID(1L));
        book.setBookName("test");
        bookDAO.create(book);
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Book/Init.xml")
    @ExpectedDatabase("classpath:Book/Update.xml")
    public void testUpdate() throws Exception {
        Book book = bookDAO.findByID(1L);
        book.setBookName("test");
        bookDAO.update(book);
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Book/Init.xml")
    @ExpectedDatabase("classpath:Book/Delete.xml")
    public void testDelete() throws Exception {
        Book book = bookDAO.findByID(1L);
        bookDAO.delete(book);
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Book/Init.xml")
    public void testFindById() throws Exception {
        Book book = bookDAO.findByID(1L);
        Assert.assertEquals(new Long(1), book.getBookId());
        Assert.assertEquals("book", book.getBookName());
        Assert.assertEquals(new Long(1), book.getCategory().getCategoryId());
        Assert.assertEquals(new Long(1), book.getCell().getCellId());
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Book/Init.xml")
    public void testFindAll() throws Exception {
        List<Book> books = bookDAO.findAll();
        Assert.assertEquals(new Long(1), books.get(0).getBookId());
        Assert.assertEquals("book", books.get(0).getBookName());
        Assert.assertEquals(new Long(1), books.get(0).getCategory().getCategoryId());
        Assert.assertEquals(new Long(1), books.get(0).getCell().getCellId());
    }

    @Test
    @DirtiesContext
    @Rollback(false)
    @DatabaseSetup("classpath:Book/Init.xml")
    public void testFinByCategory() throws Exception {
        List<Book> books = bookDAO.findByCategory("a");
        Assert.assertEquals(new Long(1), books.get(0).getBookId());
        Assert.assertEquals("book", books.get(0).getBookName());
        Assert.assertEquals(new Long(1), books.get(0).getCategory().getCategoryId());
        Assert.assertEquals(new Long(1), books.get(0).getCell().getCellId());
    }
}
