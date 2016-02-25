package main.java.com.nixsolutions;

import java.io.File;
import java.io.IOException;

/**
 * Created by sobolenko on 2/23/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Program newProgram = new Program();
        File trace = new File("Trace.dat");
        newProgram.sendCommands("frfflf", trace);
    }
}
