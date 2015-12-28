package com.nixsolutions;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import com.nixsolutions.dao.impl.CellDaoImpl;
import com.nixsolutions.entity.Cell;

public class CellDaoTest extends DBUnitConfig {

	public CellDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataFileLoader().load("/cell/CellInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
	public void testShouldretrieveCellById() throws Exception {
		Cell cell = new CellDaoImpl().getCellById(1);
		Assert.assertEquals(new Integer(1), cell.getCellId());
		Assert.assertEquals("cell A", cell.getName());
	}
	
	public void testShouldReturnNullIfQueryReturnedNoResultsCellById() throws Exception {
		Cell auth = new CellDaoImpl().getCellById(5);
		Assert.assertNull(auth);
	}
	
	public void testShouldRetrieveAllUthors() throws Exception {
		List<Cell> cells = new CellDaoImpl().getAllCells();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/cell/CellInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("cell"), actualData.getTable("cell"));
		Assert.assertEquals(expectedData.getTable("cell").getRowCount(),cells.size());
	}

	public void testShouldDeleteCell() throws Exception {
		Cell cell = new CellDaoImpl().getCellById(1);
		new CellDaoImpl().deleteCell(cell);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/cell/CellDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("cell"), actualData.getTable("cell"));
	}

	public void testShouldCreateCell() throws Exception {
		Cell drama = new Cell("cell D");
		new CellDaoImpl().createCell(drama);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/cell/CellCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("cell"), 
	            expectedData.getTable("cell").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("cell"), filteredTable); 
	}

	public void testShouldUpdateCell() throws Exception {
		Cell updCell = new CellDaoImpl().getCellById(3);
		updCell.setName("cell E");
		new CellDaoImpl().updateCell(updCell);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/cell/CellUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("cell"), 
	            expectedData.getTable("cell").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("cell"), filteredTable);
	}
}
