/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author mednorcom
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({mocksworkshop.RobotMockTest.class, mocksworkshop.ProgramMockTest.class,
    mocksworkshop.RobotAndProgramTest.class})
public class MocksTestSuite {

}
