package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private static final Logger LOG = LogManager.getLogger(Program.class);
    private Robot robot;

    public Program(Robot robot, OutputStream outputStream) {
        this.robot = robot;
        robot.setBr(outputStream);
    }

    public void executeCommand(String command) {
        if (isCorrectLine(command)) {
            moveRobot(command);
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
