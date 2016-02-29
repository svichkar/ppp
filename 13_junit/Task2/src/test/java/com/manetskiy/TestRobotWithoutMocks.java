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

public class TestRobotWithoutMocks {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void shouldExecuteCommands() throws IOException {
        //given
        Robot robot = new Robot();
        File file = temporaryFolder.newFile("robot.txt");
        Program program = new Program(new FileOutputStream(file), robot);
        //when
        program.executeCommand("lffrZflf");
        List<String> commands;
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            commands = reader.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            throw new IOException(ex);
        }
        //then
        assertEquals("X: 0; Y: 1; Direction: NORTH", commands.get(0));
        assertEquals("X: 0; Y: 2; Direction: NORTH", commands.get(1));
        assertEquals("No action. Unknown command - Z", commands.get(2));
        assertEquals("X: 1; Y: 2; Direction: EAST", commands.get(3));
        assertEquals("X: 1; Y: 3; Direction: NORTH", commands.get(4));

    }
}