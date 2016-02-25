package main.java.com.nixsolutions;

import java.io.*;

/**
 * Created by sobolenko on 2/25/2016.
 */
public class Program {
    FileOutputStream trace;
    BufferedWriter traceWritter;
    Robot myRobot;
    private String[] commands;

    public void sendCommands(String commands, File taceFile) throws IOException {
        this.commands = commands.split("", commands.length());
        trace = new FileOutputStream(taceFile);
        traceWritter = new BufferedWriter(new OutputStreamWriter(trace));
        myRobot = new Robot(trace, traceWritter);
        executeCommand();
    }

    private void executeCommand() throws IOException {
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
        }
        try {
            trace.flush();
            trace.close();
            traceWritter.flush();
            traceWritter.close();
        } catch (IOException ioExc) {
            trace.flush();
            trace.close();
            traceWritter.flush();
            traceWritter.close();
            throw new IOException(ioExc);
        }
    }
}
