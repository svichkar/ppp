package com.nixsolutions;

import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.impl.AuthorDaoImpl;
import com.nixsolutions.entity.Author;

public class AuthorDaoTest extends DBUnitConfig {

	public AuthorDaoTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("LibraryTestDataSet.xml"));
		tester.setDataSet(beforeData);
		tester.onSetup();
	}

	@Test
	public void shouldretrieveAuthorById() throws Exception {
		Author auth = new AuthorDaoImpl().getAuthor(1);
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("LibraryTestDataSet.xml"));

		IDataSet actualData = tester.getConnection().createDataSet();
		// Assertion.assertEquals(expectedData, actualData);
		// Assert.assertEquals(expectedData.getTable("author").getRowCount(),persons.size());
	}

	@Test
	public void shouldRetrieveAllUthors() throws Exception {
		List<Author> authors = new AuthorDaoImpl().getAllAuthors();
		IDataSet expectedData = new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("LibraryTestDataSet.xml"));

		//IDataSet actualData = tester.getConnection().createDataSet();
		Assert.assertEquals(expectedData.getTable("author").getRowCount(),authors.size());
	}

	@Test
	public void shouldDeleteAuthor() {

	}

	@Test
	public void shouldCreateAuthor() {

	}

	@Test
	public void shouldUpdateAuthor() {

	}
}
