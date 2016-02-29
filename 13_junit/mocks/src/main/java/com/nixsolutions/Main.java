package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String path = new File("").getAbsolutePath() + "TempFile";
        if(!Files.exists(Paths.get(path)))
            Files.createFile(Paths.get(path));
        File file = new File(new File("").getAbsolutePath(), "TempFile");
        Program program = new Program();
        program.setFile(file);
        program.setupRobot();
        program.setupRobotProgram("fflffrfflllffrff");
        program.closeStream();
    }
}
