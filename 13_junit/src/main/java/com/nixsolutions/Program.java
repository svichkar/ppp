package main.java.com.nixsolutions;

import java.io.*;

/**
 * Created by sobolenko on 2/25/2016.
 */
public class Program {
    FileOutputStream fout;
    Robot myRobot;
    BufferedWriter traceWritter;
    private String[] commands;

    public void sendCommands(String commands, File traceFile, Robot robot) throws IOException {
        this.commands = commands.split("", commands.length());
        fout = new FileOutputStream(traceFile);
        //myRobot = new Robot(trace);
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
                fout.write(myRobot.getTrace().toByteArray());
            }

        } catch (IOException e) {
            fout.flush();
            fout.close();
            throw new IOException(e);
        }
        fout.flush();
        fout.close();
    }
}
