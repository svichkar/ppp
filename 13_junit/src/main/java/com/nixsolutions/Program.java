package main.java.com.nixsolutions;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sobolenko on 2/25/2016.
 */
public class Program {
    FileWriter fout;
    Robot myRobot;
    private String[] commands;

    public void sendCommands(String commands, FileWriter fout, Robot robot) throws IOException {
        this.commands = commands.split("", commands.length());
        this.fout = fout;
        this.myRobot = robot;
        executeCommand();
    }

    private void executeCommand() throws IOException {
        try {
            for (String command : commands) {
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
        fout.flush();
        fout.close();
    }

    private void writeToFile() throws IOException {
        try {
            fout.write(myRobot.getTrace().toString());
        } catch (IOException e) {
            fout.flush();
            fout.close();
            throw new IOException(e);
        }
    }
}