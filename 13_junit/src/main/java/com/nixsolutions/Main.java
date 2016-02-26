package main.java.com.nixsolutions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sobolenko on 2/23/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Program newProgram = new Program();
        File trace = new File("Trace.dat");
        Robot myRobot = new Robot(new ByteArrayOutputStream());
        newProgram.sendCommands("frfflf", trace, myRobot);
    }
}
