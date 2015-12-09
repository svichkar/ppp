import java.io.IOException;

/**
 * Created by rybkinrolla on 04.12.2015.
 */
public class Program {
    private Robot robot;

    public Program(Robot robot) {
        this.robot = robot;
    }

    public void loadProgramInsideRobot(String program) throws IOException {
        char[] arr = program.toLowerCase().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case 'r':
                    robot.turnRight();
                    break;
                case 'l':
                    robot.turnLeft();
                    break;
                case 'f':
                    robot.stepForward();
                    break;
                default:
                    break;
            }
        }
    }
}
