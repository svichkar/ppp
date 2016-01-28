package com.nixsolutions;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Category;

public class CategoryDaoTest extends DBUnitConfig {
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	
	public CategoryDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		//beforeData = new FlatXmlDataFileLoader().load("/category/CategoryInitialDataSet.xml");
		beforeData = new FlatXmlDataFileLoader().load("/book/BookInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
	public void testShouldretrieveCategoryById() throws Exception {
		Category category = factory.getCategoryDao().getCategoryById(1l);
		Assert.assertEquals(new Long(1), category.getCategoryId());
		Assert.assertEquals("Horror", category.getName());
	}
	
	public void testShouldReturnNullIfQueryReturnedNoResultsCategoryById() throws Exception {
		Category auth = factory.getCategoryDao().getCategoryById(5l);
		Assert.assertNull(auth);
	}
	
	public void testShouldRetrieveAllUthors() throws Exception {
		List<Category> categories = factory.getCategoryDao().getAllCategories();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("category"), actualData.getTable("category"));
		Assert.assertEquals(expectedData.getTable("category").getRowCount(),categories.size());
	}
/*
	public void testShouldDeleteCategory() throws Exception {
		Category category = factory.getCategoryDao().getCategoryById(1l);
		factory.getCategoryDao().deleteCategory(category);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("category"), actualData.getTable("category"));
	}
*/
	public void testShouldCreateCategory() throws Exception {
		Category drama = new Category("Drama");
		factory.getCategoryDao().createCategory(drama);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("category"), 
	            expectedData.getTable("category").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("category"), filteredTable); 
	}

	public void testShouldUpdateCategory() throws Exception {
		Category updCategory = factory.getCategoryDao().getCategoryById(3l);
		updCategory.setName("SciFi");
		factory.getCategoryDao().updateCategory(updCategory);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("category"), 
	            expectedData.getTable("category").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("category"), filteredTable);
	}
}
