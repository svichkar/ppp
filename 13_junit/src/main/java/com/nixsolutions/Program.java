package com.nixsolutions;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sobolenko on 2/25/2016.
 */
public class Program {
    FileOutputStream fout;
    Robot myRobot;

    public Program(FileOutputStream fout, Robot robot) throws IOException {
        this.fout = fout;
        this.myRobot = robot;
    }

    public void executeCommand(String commands) throws IOException {
        String[] slittedCommands = commands.split("", commands.length());
        try {
            for (String command : slittedCommands) {
                switch (command) {
                    case "l":
                        myRobot.turnLeft();
                        break;
                    case "r":
                        myRobot.turnRight();
                        break;
                    case "f":
                        myRobot.stepForward();
                        break;
                }
                writeToFile();
            }

        } catch (IOException e) {
            fout.flush();
            fout.close();
            throw new IOException(e);
        }
        if (fout != null) {
            fout.flush();
            fout.close();
        }
    }

    private void writeToFile() throws IOException {
        try {
            myRobot.getTrace();
        } catch (IOException e) {
            fout.flush();
            fout.close();
            throw new IOException(e);
        }
    }
}