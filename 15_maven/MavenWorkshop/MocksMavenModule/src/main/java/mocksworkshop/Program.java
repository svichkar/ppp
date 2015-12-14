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
public class Program {

    private Robot robot;

    public Robot getRobot() {
        return robot;
    }

    public Program(Robot robot) {
        this.robot = robot;
    }

    public void executeCommand(String inputCommands) throws IOException {
        char[] commandArray = inputCommands.toCharArray();
        for (char command : commandArray) {
            switch (command) {
                case 'l':
                    robot.turnLeft();
                    break;
                case 'r':
                    robot.turnRight();
                    break;
                case 'f':
                    robot.stepForward();
                    break;
            }
        }
    }

}
