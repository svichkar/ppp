package com.nixsolutions;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.entity.Author;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:persistence-beans-test.xml")
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
   // TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class
    })
@DatabaseSetup("/book/BookInitialDataSet.xml")
@DatabaseTearDown("/book/BookInitialDataSet.xml")
public class AuthorDaoTest {

    @Autowired
    public AuthorDao authorDao;

    @Test
    public void testShouldretrieveAuthorById() throws Exception {
		Author auth = authorDao.getAuthorById(1l);
		Assert.assertEquals(new Long(1), auth.getAuthorId());
		Assert.assertEquals("Stephen", auth.getFirstName());
		Assert.assertEquals("King", auth.getSecondName());
	}
    
  //  @Test  
    @ExpectedDatabase("/book/BookInitialDataSet.xml")
	public void testShouldRetrieveAllUthors() throws Exception {
		List<Author> authors = authorDao.getAllAuthors();
	}
    
 //   @Test
    @ExpectedDatabase("/author/AuthorDelete.xml")
    @Transactional
    public void testShouldDeleteAuthor() throws Exception {
		Author author = authorDao.getAuthorById(1l);
		authorDao.deleteAuthor(author);
	}

  //  @Test
    @ExpectedDatabase("/author/AuthorCreate.xml")
   @Transactional
	public void testShouldCreateAuthor() throws Exception {
		Author authMiha = new Author();
		authMiha.setFirstName("Michail");
		authMiha.setSecondName("Lelyakov");
		authorDao.createAuthor(authMiha);
	}

  //  @Test
    @ExpectedDatabase("/author/AuthorUpdate.xml")
   // @Transactional
	public void testShouldUpdateAuthor() throws Exception {
		Author updAuth = authorDao.getAuthorById(3l);
		updAuth.setFirstName("Michail");
		updAuth.setSecondName("Lelyakov");
		authorDao.updateAuthor(updAuth);
	}
}