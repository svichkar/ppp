package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.AuthorDAO;
import com.nixsolutions.library.entity.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;



/**
 * Created by kozlovskij on 12/24/2015.
 */
public class TestAuthor extends DBUnitConfig {
    private Author author = new Author("Aleksandr", "Pushkin");

    public TestAuthor(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/Author/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate () throws Exception {
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        authorDAO.create(author);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Author/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("author"),
                expected.getTableMetaData("author").getColumns());
        Assertion.assertEquals(expected.getTable("author"),filteredTable);
    }
}
