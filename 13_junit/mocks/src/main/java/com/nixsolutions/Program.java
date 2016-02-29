package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sirotkin Mikhail
 */
public class Program {
    private Robot robot;
    private FileWriter fileWriter;
    private File file;

    /**
     * Empty constructor
     */
    public Program() {}

    public void setFile (File file) throws IOException {
        this.file = file;
        this.fileWriter = new FileWriter(this.file);
    }
    /**
     * Set Robot for this class
     * @param robot
     */
    public void setRobot (Robot robot) {
        this.robot = robot;
    }
    /**
     * Setup new Robot instance using current fileWriter like an argument
     */
    public void setupRobot () throws IOException {
        this.robot = new Robot(fileWriter);
    }

    /**
     *  Get current Robot instance
     * @return
     */
    public Robot getRobot () {
        return robot;
    }

    /**
     * Save all data to the file, and flush and close the stream
     * @throws IOException
     */
    public void closeStream() throws IOException {
        robot.writer.close();
    }

    /**
     * Method setup circle of robot actions and execute them
     * If we have char defferent from 'f', 'l' or 'r' we ignore it
     * @param inputCommands like a line of chars flr (foeward-left-right)
     * @throws IOException
     */
    public void setupRobotProgram(String inputCommands) throws IOException {
        char[] arr = inputCommands.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case 'f':
                    robot.stepForward();
                    break;
                case 'l':
                    robot.turnLeft();
                    break;
                case 'r':
                    robot.turnRight();
                    break;
                default:
                    //ignore wrong char
                    break;
            }
        }
        fileWriter.flush();
    }
}
