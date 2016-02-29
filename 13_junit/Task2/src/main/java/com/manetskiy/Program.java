package com.manetskiy;

import java.io.*;


/**
 * Performs manipulations with Robot and logs its actions.
 */
public class Program {
    private FileOutputStream out = null;
    private Robot robot;


    /**
     * Initializes <code>out</code> and <code>robot</code> and passes <code>out</code> to <code>robot</code>.
     *
     * @param out   FileOutputStream for logging robot's actions to file.
     * @param robot Robot that executes commands.
     */
    public Program(FileOutputStream out, Robot robot) {
        this.out = out;
        this.robot = robot;
        robot.setOutputStream(out);
    }


    /**
     * Moves robot according to chain of commands.
     *
     * <p>Following commands can be recognized by <code>robot</code>:</p>
     * <p>f - to move forward</p>
     * <p>r - to turn right</p>
     * <p>l - to turn left.</p>
     *
     * <p>If command is not recognized then robot won't do any action
     * and its position and direction won't change. Unrecognized command will be
     * logged in the file of robot's trace.</p>
     *
     * <p>Closes <code>out</code> when all commands have been executed.</p>
     *
     * @param command Chain of commands to execute.
     * @throws IOException
     */
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
