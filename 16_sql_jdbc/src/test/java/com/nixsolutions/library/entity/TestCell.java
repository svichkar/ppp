package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.CellDAO;
import com.nixsolutions.library.entity.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Serko on 26.12.2015.
 */
public class TestCell extends DBUnitConfig {
    private Cell cell;

    public TestCell(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/Cell/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        cell = new Cell("C");
        CellDAO cellDAO = daoFactory.getCellDAO();
        cellDAO.create(cell);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Cell/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("cell"),
                expected.getTableMetaData("cell").getColumns());
        Assertion.assertEquals(expected.getTable("cell"), filteredTable);
    }

    public void testUpdate() throws Exception {
        cell = new Cell(1, "C");
        CellDAO cellDAO = daoFactory.getCellDAO();
        cellDAO.update(cell);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Cell/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("cell"),
                expected.getTableMetaData("cell").getColumns());
        Assertion.assertEquals(expected.getTable("cell"), filteredTable);
    }

    public void testDelete() throws Exception {
        cell = new Cell(1, "A");
        CellDAO cellDAO = daoFactory.getCellDAO();
        cellDAO.delete(cell);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Cell/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("cell"),
                expected.getTableMetaData("cell").getColumns());
        Assertion.assertEquals(expected.getTable("cell"), filteredTable);
    }

    public void testFindById() throws Exception {
        cell = new Cell(1, "A");
        CellDAO cellDAO = daoFactory.getCellDAO();
        Cell actualCell;
        actualCell = cellDAO.findByID(cell.getCellId());

        Assert.assertEquals(cell.getCellId(), actualCell.getCellId());
        Assert.assertEquals(cell.getName(), actualCell.getName());
    }

    public void testFindAll() throws Exception {
        CellDAO cellDAO = daoFactory.getCellDAO();
        List<Cell> cellList;
        cellList = cellDAO.findAll();

        Assert.assertEquals(2, cellList.size());

        Assert.assertEquals(new Integer(1), cellList.get(0).getCellId());
        Assert.assertEquals("A", cellList.get(0).getName());

        Assert.assertEquals(new Integer(2), cellList.get(1).getCellId());
        Assert.assertEquals("B", cellList.get(1).getName());
    }
}
