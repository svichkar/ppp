package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private static final Logger LOG = LogManager.getLogger(Program.class);
    private Robot robot;
    private ByteArrayOutputStream bw;

    public Program() {
        bw = new ByteArrayOutputStream();
        robot = new Robot(bw);
    }

    public void executeCommand(String command, File file) {
        if (isCorrectLine(command)) {
            moveRobot(command);
            writeToFile(file);
        }
    }

    public void moveRobot(String way) {
        for (String c : way.split("")) {
            switch (c) {
                case "l": {
                    robot.turnLeft();
                    break;
                }
                case "r": {
                    robot.turnRight();
                    break;
                }
                case "f": {
                    robot.stepForward();
                    break;
                }
                default: {
                    LOG.warn("In default block. Invalid behaviour.");
                }
            }
        }
    }

    public void writeToFile(File file) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bw.toByteArray());
        } catch (FileNotFoundException e) {
            LOG.catching(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCorrectLine(String way) {
        List<String> correctLetters = new ArrayList<>();
        correctLetters.add("l");
        correctLetters.add("r");
        correctLetters.add("f");

        for (String c : way.split("")) {
            if (!correctLetters.contains(c))
                return false;
        }
        return true;
    }

}
