package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/** Program for manipulation of {@code Robot}. */
public class Program {
    private Robot robot;
    private Writer writer;

    /**
     * Creates a {@code Robot} and provides it with {@code Writer}, which writes
     * the results of execution to a provided {@code file}.
     * 
     * @param file
     *            File to write the path traveled by {@code Robot}.
     * @return Returns created {@code Robot}.
     * @throws IOException
     */
    public Robot createRobot(File file) throws IOException {
        writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        robot = new Robot(writer);
        return robot;
    }

    /**
     * Creates a {@code Robot} and executes provided commands on it.
     * 
     * @param commands
     *            {@code String} with commands to a {@code Robot}, where 'l' is
     *            a command to turn left, 'r' - to turn right, 'f' - to make one
     *            step forward. Command code characters are not separated by
     *            anything, so that command sequence is represented by a simple
     *            plain string.
     * @param file
     *            File to write the path traveled by {@code Robot}.
     * @throws IOException
     */
    public void recordExecutionOfNewRobot(String commands, File file)
            throws IOException {
        manipulateRobot(createRobot(file), commands);
        writer.flush();
        writer.close();
    }

    /**
     * Manipulates a provided {@code Robot} based on a provided string with
     * commands.
     * 
     * @param robot
     *            {@code Robot} to manipulate.
     * @param commands
     *            {@code String} with commands to a {@code Robot}, where 'l' is
     *            a command to turn left, 'r' - to turn right, 'f' - to make one
     *            step forward. Command code characters are not separated by
     *            anything, so that command sequence is represented by a simple
     *            plain string.
     * @throws IOException
     */
    public void manipulateRobot(Robot robot, String commands)
            throws IOException {
        for (char c : commands.toLowerCase().toCharArray()) {
            if (c == 'l') {
                robot.turnLeft();
            } else if (c == 'r') {
                robot.turnRight();
            } else if (c == 'f') {
                robot.stepForward();
            } else {
                throw new IllegalArgumentException(
                        "Illegal command code: [" + c + "]");
            }
        }
    }
}
