package com.nixsolutions;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;
import com.nixsolutions.dao.impl.CategoryDaoImpl;
import com.nixsolutions.entity.Category;

public class CategoryDaoTest extends DBUnitConfig {

	public CategoryDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataFileLoader().load("/category/CategoryInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
	public void testShouldretrieveCategoryById() throws Exception {
		Category category = new CategoryDaoImpl().getCategoryById(1);
		Assert.assertEquals(new Integer(1), category.getCategoryId());
		Assert.assertEquals("Horror", category.getName());
	}
	
	public void testShouldReturnNullIfQueryReturnedNoResultsCategoryById() throws Exception {
		Category auth = new CategoryDaoImpl().getCategoryById(5);
		Assert.assertNull(auth);
	}
	
	public void testShouldRetrieveAllUthors() throws Exception {
		List<Category> categories = new CategoryDaoImpl().getAllCategories();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("category"), actualData.getTable("category"));
		Assert.assertEquals(expectedData.getTable("category").getRowCount(),categories.size());
	}

	public void testShouldDeleteCategory() throws Exception {
		Category category = new CategoryDaoImpl().getCategoryById(1);
		new CategoryDaoImpl().deleteCategory(category);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("category"), actualData.getTable("category"));
	}

	public void testShouldCreateCategory() throws Exception {
		Category drama = new Category("Drama");
		new CategoryDaoImpl().createCategory(drama);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("category"), 
	            expectedData.getTable("category").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("category"), filteredTable); 
	}

	public void testShouldUpdateCategory() throws Exception {
		Category updCategory = new CategoryDaoImpl().getCategoryById(3);
		updCategory.setName("SciFi");
		new CategoryDaoImpl().updateCategory(updCategory);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/category/CategoryUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("category"), 
	            expectedData.getTable("category").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("category"), filteredTable);
	}
}
