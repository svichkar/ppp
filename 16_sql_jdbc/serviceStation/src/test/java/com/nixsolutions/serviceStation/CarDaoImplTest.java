package com.nixsolutions.serviceStation;

import java.sql.SQLException;

import org.junit.Before;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.h2Objects.CarDaoImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CarDaoImplTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	
    public CarDaoImplTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CarDaoImplTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
         assertTrue( true );
    }
}
