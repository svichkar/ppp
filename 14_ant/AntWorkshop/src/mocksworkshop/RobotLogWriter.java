/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import java.io.IOException;

/**
 *
 * @author mednorcom
 */
public interface RobotLogWriter {

    public void writeLog(String loggingIssue) throws IOException;
}
