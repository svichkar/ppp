package com.nixsolutions.mock;


/**
 * Created by svichkar on 12/5/2015.
 */
public class Program {

    private Robot robot;

    public Program() {
        robot = new Robot();
        doWork();
    }

     protected void doWork () {

        robot.turnLeft();
        robot.stepForward();
        robot.stepForward();
        robot.turnRight();
        robot.stepForward();
        robot.turnLeft();
        robot.stepForward();
        robot.turnRight();
        robot.turnRight();
        robot.stepForward();
        robot.stepForward();
        robot.stepForward();
    }
}
