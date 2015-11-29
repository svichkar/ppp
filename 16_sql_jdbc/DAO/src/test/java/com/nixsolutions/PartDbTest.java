package com.nixsolutions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.app.ConnectionManager;
import com.nixsolutions.dao.impl.CarDAOImpl;
import com.nixsolutions.dao.impl.PartDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.Part;

public class PartDbTest extends DBTestCase {

	private String pathToFile;
	private QueryDataSet partialDS;
	private ServiceStationDAOFactoryImpl ssFactory;

	public PartDbTest() throws Exception {
		super();
		Class cls = Class.forName("org.h2.Driver");
		// /path to xml file
		pathToFile = cls.getClassLoader()
				.getResource("com/nixsolutions/part.xml").getFile();

		// define default settings
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				"org.h2.Driver");
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				"jdbc:h2:tcp://localhost/~/sqllab");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				"sa");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				"");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
				"SQLLAB");

		IDatabaseConnection idbconn = new DatabaseConnection(
				ConnectionManager.getConnection());
		idbconn.getConfig().setProperty(
				DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
				new H2DataTypeFactory());

		// init factory
		ssFactory = new ServiceStationDAOFactoryImpl();

		partialDS = new QueryDataSet(idbconn);
		partialDS.addTable("PART", "SELECT * FROM SQLLAB.part");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File(pathToFile));
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}

	protected void setUpDatabaseConfig(DatabaseConfig config) {

	}

	@Before
	protected void setUp() throws DataSetException, IOException {
		try (FileOutputStream fos = new FileOutputStream(pathToFile)) {
			FlatXmlDataSet.write(partialDS, fos);
			fos.flush();
			fos.close();
		} catch (IOException ex) {
			throw new IOException(ex.getMessage());
		}
	}

	@Test
	public void testAddPart() throws DataSetException, SQLException, Exception {
		PartDAOImpl partDAO = (PartDAOImpl) ssFactory.getDao(Part.class);
		Part p1 = new Part();
		p1.setPart_name("Bolt");
		p1.setAmount(1000);
		p1.setVendor("XTZ");
		partDAO.create(p1);

		IDataSet ds = getConnection().createDataSet(new String[] { "PART" });
		ITable tActual = ds.getTable("PART");

		Assert.assertEquals(p1.getPart_name(),
				tActual.getValue(tActual.getRowCount() - 1, "part_name"));
	}

	@Test
	public void testUpdatePart() throws DataSetException, SQLException,
			Exception {
		PartDAOImpl partDAO = (PartDAOImpl) ssFactory.getDao(Part.class);
		Part p1 = new Part();
		p1.setPart_name("Bolt");
		p1.setAmount(1000);
		p1.setVendor("XTZ");
		partDAO.create(p1);

		Part p2 = partDAO.getAll().get(partDAO.getAll().size()-1);
		p2.setAmount(2000);
		partDAO.update(p2);

		IDataSet ds = getConnection().createDataSet(new String[] { "PART" });
		ITable tActual = ds.getTable("PART");

		Assert.assertEquals(BigInteger.valueOf(p2.getAmount()),
				tActual.getValue(tActual.getRowCount() - 1, "amount"));
	}
}
