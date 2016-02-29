package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/** Program for manipulation of {@code Robot}. */
public class Program {
    private File file;
    private Writer writer;

    /**
     * Initializes a newly created {@code Program}.
     * 
     * @param outputFile
     *            File to generate with path traveled by {@code Robot}.
     * @throws IOException
     */
    public Program(File outputFile) throws IOException {
        this.file = outputFile;
    }

    /**
     * Manipulates a {@code Robot} based on provided string with commands.
     * 
     * @param commands
     *            String with commands to a {@code Robot}, where 'l' is a
     *            command to turn left, 'r' - to turn right, 'f' - to make one
     *            step forward. Command code characters are not separated by
     *            anything, so that command sequence is represented by a simple
     *            plain string.
     * @throws IOException
     */
    public void manipulateRobot(String commands) throws IOException {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
                    "UTF-8"));
            Robot robot = new Robot(writer);
            
            for (char c : commands.toCharArray()) {
                if (c == 'l') {
                    robot.turnLeft();
                } else if (c == 'r') {
                    robot.turnRight();
                } else if (c == 'f') {
                    robot.stepForward();
                } else {
                    throw new IllegalArgumentException("Illegal command code: [" + c + "]");
                }
            }
            writer.flush();
        } finally {
            writer.close();
        }
    }
}
