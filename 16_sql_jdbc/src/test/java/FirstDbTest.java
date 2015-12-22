import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;


/**
 * Created by svichkar on 12/22/2015.
 */
public class FirstDbTest extends JdbcBasedDBTestCase {
/*
    public FirstDbTest(String testName) throws Exception {
        super(testName);

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                );
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:h2:tcp://localhost/~/sqllab");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
                "public");

    }*/

    @Override
    protected String getConnectionUrl() {
        return "jdbc:h2:tcp://localhost/~/sqllab";
    }

    @Override
    protected String getDriverClass() {
        return "org.h2.Driver";
    }

    @Override
    protected IDataSet getDataSet() throws Exception {

        return new FlatXmlDataSet(getClass().getResourceAsStream("/GradeTestDataSet.xml"));
    }

    public void testGradeFindAll () throws Exception {


        IDataSet s = getConnection().createDataSet();

       String[] ss = s.getTableNames();



    }


}
