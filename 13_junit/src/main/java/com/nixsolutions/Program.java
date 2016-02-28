package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private static final Logger LOG = LogManager.getLogger(Program.class);
    private ByteArrayOutputStream bw;
    private FileOutputStream fileOutputStream;
    private Robot robot;

    public Program(Robot robot, FileOutputStream fileOutputStream) {
        this.robot = robot;
        this.fileOutputStream = fileOutputStream;
        bw = new ByteArrayOutputStream();
        robot.setBr(bw);
    }

    public void executeCommand(String command) {
        if (isCorrectLine(command)) {
            moveRobot(command);
            writeToFile();
        }
    }

    private void moveRobot(String way) {
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

    private void writeToFile() {
        try {
            fileOutputStream.write(bw.toByteArray());
        } catch (IOException e) {
            LOG.catching(e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    LOG.warn("Exception in the finally block");
                }
            }
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
