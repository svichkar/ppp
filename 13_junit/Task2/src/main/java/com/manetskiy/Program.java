package com.manetskiy;

import java.io.*;

public class Program {
    private FileOutputStream out = null;
    private Robot robot;

    public Program(FileOutputStream out, Robot robot) {
        this.out = out;
        this.robot = robot;
        robot.setOutputStream(out);
    }

    protected void executeCommand(String command) throws IOException {
        char[] commands = command.toCharArray();
        try {
            for (int i = 0; i < commands.length; i++) {
                char ch = commands[i];
                switch (ch) {
                    case 'f':
                        robot.stepForward();
                        break;
                    case 'r':
                        robot.turnRight();
                        break;
                    case 'l':
                        robot.turnLeft();
                        break;
                    default:
                        out.write(("No action. Unknown command - " + ch + "\n").getBytes());
                        break;
                }
            }
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            if (out != null)
                out.close();
        }
    }
}
