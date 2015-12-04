/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mednorcom
 */
public class RobotAndProgramTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCoordinates method, of class Robot.
     */
    @Test
    public void testGetCoordinates() {
        System.out.println("getCoordinates");
        Robot instance = null;
        String expResult = "";
        String result = instance.getCoordinates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stepForward method, of class Robot.
     */
    @Test
    public void testStepForward() throws Exception {
        System.out.println("stepForward");
        Robot instance = null;
        instance.stepForward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of turnRight method, of class Robot.
     */
    @Test
    public void testTurnRight() {
        System.out.println("turnRight");
        Robot instance = null;
        instance.turnRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of turnLeft method, of class Robot.
     */
    @Test
    public void testTurnLeft() {
        System.out.println("turnLeft");
        Robot instance = null;
        instance.turnLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
