package com.manetskiy;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestRobotWithoutMocks {

    private Program program;
    private Robot robot;

    //TODO
    @Test
    public void shouldExecuteCommands() throws IOException{
        //given
        robot = new Robot();
        program = new Program(new FileOutputStream(new File("robotTest.txt")), robot);
    }
}
