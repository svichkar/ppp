package main.java.com.nixsolutions;

/**
 * Created by sobolenko on 12/21/2015.
 */
public class Robot {
    private String[] commands;
    private int Xcoordinate = 0;
    private int Ycoordinate = 0;
    private int direction = 90;

    public void sendCommands(String commands) {
        this.commands = commands.split("", commands.length());
        executeCommand();
    }

    private void executeCommand() {
        for (String command : commands) {
            switch (command) {
                case "l":
                    turnLeft();
                    break;
                case "r":
                    turnRight();
                    break;
                case "f":
                    stepForward();
                    break;
            }
        }
    }

    public void stepForward() {
        if (direction == 360) {
            direction = 0;
        }
        switch (direction) {
            case 0:
                Ycoordinate++;
                break;
            case 90:
                Xcoordinate++;
                break;
            case 180:
                Ycoordinate--;
                break;
            case 270:
                Xcoordinate--;
                break;
        }
        System.out.println(Xcoordinate+" "+Ycoordinate);
    }

    public void turnLeft() {
        direction += -90;
    }

    public void turnRight() {
        direction += 90;
    }

    public String getCoordinates() {
        return Xcoordinate + "," + Ycoordinate;
    }
}
