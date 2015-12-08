package com.nixsolutions;

import java.io.IOException;

/**
 * @author Sirotkin Mikhail
 */
public class Program {
    private Robot robot;
    /**
     * Constructor
     * @param robot
     */
    public Program(Robot robot) {
        this.robot = robot;
    }

    /**
     * Method setup circle of robot actions and execute them
     * If we have char defferent from 'f', 'l' or 'r' we ignore it
     * @param inputCommads like a line of chars flr (foeward-left-right)
     * @throws IOException
     */
    public void setupRobotProgram(String inputCommads) throws IOException {
        char[] arr = inputCommads.toCharArray();
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
    }
}
