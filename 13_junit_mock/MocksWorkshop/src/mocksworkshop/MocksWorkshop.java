/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mednorcom
 */
public class MocksWorkshop {

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LOGGER.entry(args);
        Robot myRobo = new Robot("E:\\robo.log");
        Program myProgram = new Program(myRobo);
        try {
            myProgram.executeCommand("lllllf");
        } catch (IOException ex) {
            LOGGER.error("Movement log write error", ex);
        }
        LOGGER.exit();
    }

}
