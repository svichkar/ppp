package main.java.com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sobolenko on 2/25/2016.
 */
public class Program {
    FileWriter fout;
    File traceFile;
    Robot myRobot;
    private String[] commands;

    public void sendCommands(String commands, File traceFile, Robot robot) throws IOException {
        this.commands = commands.split("", commands.length());
        this.traceFile = traceFile;
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
                //Files.write(traceFile.toPath(),myRobot.getTrace().toByteArray());
                //fout.write(myRobot.getTrace().toString());
                myRobot.writeToFileCommand();
            }

        } catch (IOException e) {
            fout.flush();
            fout.close();
            throw new IOException(e);
        }
        fout.flush();
        fout.close();
    }

    public void writeToFile() throws IOException {
        try {
            if (fout == null) {
                fout = new FileWriter(traceFile);
            }
            fout.write(myRobot.getTrace().toString());
        } catch (IOException e) {
            fout.flush();
            fout.close();
            throw new IOException(e);
        }
    }
}