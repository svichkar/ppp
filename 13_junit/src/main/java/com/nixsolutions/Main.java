package com.nixsolutions;

import java.io.*;

/**
 * Created by sobolenko on 2/23/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File trace = new File("Trace.dat");
        FileOutputStream fout = new FileOutputStream(trace);
        Robot myRobot = new Robot(fout);
        Program newProgram = new Program(fout, myRobot);
        newProgram.executeCommand("lffrflfrrfff");
    }
}
