package com.manetskiy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileOutputStream out = new FileOutputStream(new File("robot.txt"));
            Robot robot = new Robot();
            Program program = new Program(out, robot);
            program.executeCommand("fffrttlff");
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
