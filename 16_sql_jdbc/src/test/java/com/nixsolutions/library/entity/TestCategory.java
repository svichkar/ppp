package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.CategoryDAO;
import com.nixsolutions.library.entity.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Serko on 25.12.2015.
 */
public class TestCategory extends DBUnitConfig {
    private Category category;

    public TestCategory(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/Category/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        category = new Category("thriller");
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        categoryDAO.create(category);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Category/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("category"),
                expected.getTableMetaData("category").getColumns());
        Assertion.assertEquals(expected.getTable("category"), filteredTable);
    }

    public void testUpdate() throws Exception {
        category = new Category(1, "thriller");
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        categoryDAO.update(category);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Category/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("category"),
                expected.getTableMetaData("category").getColumns());
        Assertion.assertEquals(expected.getTable("category"), filteredTable);
    }

    public void testDelete() throws Exception {
        category = new Category(1, "comedy");
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        categoryDAO.delete(category);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Category/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("category"),
                expected.getTableMetaData("category").getColumns());
        Assertion.assertEquals(expected.getTable("category"), filteredTable);
    }

    public void testFindById() throws Exception {
        category = new Category(1, "comedy");
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        Category actualCategory;
        actualCategory = categoryDAO.findByID(category.getCategoryId());

        Assert.assertEquals(category.getCategoryId(), actualCategory.getCategoryId());
        Assert.assertEquals(category.getName(), actualCategory.getName());
    }

    public void testFindAll() throws Exception {
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        List<Category> categoryList;
        categoryList = categoryDAO.findAll();

        Assert.assertEquals(2, categoryList.size());

        Assert.assertEquals(new Integer(1), categoryList.get(0).getCategoryId());
        Assert.assertEquals("comedy", categoryList.get(0).getName());

        Assert.assertEquals(new Integer(2), categoryList.get(1).getCategoryId());
        Assert.assertEquals("tragedy", categoryList.get(1).getName());
    }
}
