package com.manetskiy;

import java.io.*;

public class Program {
    private File file;
    private ByteArrayOutputStream toRobot = new ByteArrayOutputStream();
    private OutputStream toFile = null;
    private Robot robot = new Robot(toRobot);

    public Program(File file) {
        this.file = file;
    }

    public void executeCommand(String command) throws IOException {
        char[] commands = command.toCharArray();
        try {
            startLogging();
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
                        toRobot.write(("No action. Unknown command - " + ch + "\n").getBytes());
                        break;
                }
            }
            logCommand();
        } finally {
            if (toFile != null)
                toFile.close();
        }
    }

    public void logCommand() throws IOException {
        toRobot.writeTo(toFile);
    }

    private void startLogging() throws IOException {
        toFile = new FileOutputStream(file);

    }
}
