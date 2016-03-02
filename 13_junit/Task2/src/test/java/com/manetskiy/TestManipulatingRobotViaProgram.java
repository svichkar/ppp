package com.manetskiy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class TestManipulatingRobotViaProgram {

    Program program;
    File file;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        Robot robot = new Robot();
        file = temporaryFolder.newFile("robot.txt");
        program = new Program(new FileOutputStream(file), robot);
    }

    @Test
    public void invalidCommandShouldBeLoggedInFile() throws IOException {
        //given
        //when
        program.executeCommand("fzl");
        List<String> commands = readCommandsFromFile();
        //then
        assertEquals("No action. Unknown command - z", commands.get(1));
    }

    @Test
    public void robotShouldExecuteGivenListOfCommands() throws IOException {
        //given
        //when
        program.executeCommand("lffrflf");
        List<String> commands = readCommandsFromFile();
        //then
        assertEquals("X: 0; Y: 1; Direction: NORTH", commands.get(0));
        assertEquals("X: 0; Y: 2; Direction: NORTH", commands.get(1));
        assertEquals("X: 1; Y: 2; Direction: EAST", commands.get(2));
        assertEquals("X: 1; Y: 3; Direction: NORTH", commands.get(3));
    }

    private List<String> readCommandsFromFile() throws IOException {
        List<String> commands;
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            commands = reader.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            throw new IOException(ex);
        }
        return commands;
    }
}