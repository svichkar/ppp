package com.nixsolutions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class TermDAOTest {
	private Connection conn;
	private IDatabaseConnection iconn;
	private TermDAO term;
	private Long termId;

	@Before
	public void setUp() throws DatabaseUnitException {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		term = DAOFactory.getTerm();
		List <Term> st = term.findAllTerms();
		termId = Long.valueOf(st.get(st.size()-1).getTermId()+1);
	}

	@After
	public void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateTerm() throws DataSetException {
		Term termNew = new Term(termId, "Autumn-2016");
		term.createTerm(termNew);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("term", "SELECT * FROM term");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("term");
		Assert.assertEquals(termNew.getTermName(), table.getValue((int) (termNew.getTermId() - 1), "term_name"));
		term.deleteTerm(termNew);
	}

	@Test
	public void shouldUpdateTerm() throws DataSetException {
		term.createTerm(new Term(termId, "Autumn-2016"));
		Term termUpdate = new Term(termId, "Autumn-2017");
		term.updateTerm(termUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("term", "SELECT * FROM term");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("term");
		Assert.assertEquals(termUpdate.getTermName(), table.getValue((int) (termUpdate.getTermId() - 1), "term_name"));
		term.deleteTerm(termUpdate);
	}

	@Test
	public void shouldDeleteTerm() throws DataSetException {
		Term termDelete = new Term(termId, "Autumn-2016");
		term.createTerm(termDelete);
		QueryDataSet qDataSetBefore = new QueryDataSet(iconn);
		qDataSetBefore.addTable("term", "SELECT * FROM term");
		IDataSet dataSetBefore = qDataSetBefore;
		ITable tableBefore = dataSetBefore.getTable("term");
		int sizeBefore = tableBefore.getRowCount();
		term.deleteTerm(termDelete);
		QueryDataSet qDataSetAfter = new QueryDataSet(iconn);
		qDataSetAfter.addTable("term", "SELECT * FROM term");
		IDataSet dataSetAfter = qDataSetAfter;
		ITable tableAfter = dataSetAfter.getTable("term");
		int sizeAfter = tableAfter.getRowCount();
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}

	@Test
	public void shouldGetAllTerms() throws DataSetException {
		List<Term> terms = term.findAllTerms();
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("term", "SELECT * FROM term");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("Term");
		Assert.assertEquals(table.getRowCount(), terms.size());
	}

	@Test
	public void shouldFindTermById() throws DataSetException {
		Term termTest = term.findTermById(Long.valueOf(3));
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("term", "SELECT * FROM term");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("term");
		Assert.assertEquals(table.getValue((int) (termTest.getTermId() - 1), "term_name"), termTest.getTermName());
	}
}
