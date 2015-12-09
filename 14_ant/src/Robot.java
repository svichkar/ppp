
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by kozlovskij on 12/4/2015.
 */
public class Robot {
    private static final Logger LOGGER = LogManager.getLogger(Robot.class);
    OutputStream stream;
    private int coordinateX = 0;
    private int coordinateY = 0;
    private int direction = 1;

    public Robot (OutputStream stream) {
    this.stream = stream;
    }

    public void turnLeft() {
        direction++;
    }

    public void turnRight() {
        direction--;
    }

    public void stepForward() {
        if (direction < 0) {
            direction = 4 - Math.abs(direction % 4);
        }
        switch (direction % 4) {
            case 0:
                coordinateY--;
                break;
            case 1:
                coordinateX++;
                break;
            case 2:
                coordinateY++;
                break;
            case 3:
                coordinateX--;
                break;
            default:
                LOGGER.error("wrong direction: " + direction);
                break;
        }
        String temp = coordinateX + "." + coordinateY + "\n";
        try {
            stream.write(temp.getBytes());
            stream.flush();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}

