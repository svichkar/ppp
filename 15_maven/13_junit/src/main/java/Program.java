package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by kozlovskij on 12/4/2015.
 */
public class Program {
    private static final Logger LOGGER = LogManager.getLogger(Program.class);
    private static Robot robot;
    public Program(Robot robot) {
        this.robot = robot;
    }

    public void run(String commandLine) {
        commandLine = commandLine.toLowerCase();
        for (int i = 0; i < commandLine.length(); i++) {
            switch (commandLine.charAt(i)) {
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
                    LOGGER.error("wrong command: " + commandLine.charAt(i));
                    break;
            }
        }
    }
}
