package com.manetskiy;

import exception.Save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaver implements Save {
    public static void main(String[] args) {
        FileSaver fileSaver = new FileSaver();
        fileSaver.save("AAA", "zzz/z/t.txt");

    }

    public void save(String inputText, String filePath) {
        File file = new File(filePath);
        if (file.isAbsolute()) {
            if (file.exists()) {
                throw new FileExsistsException(file.getAbsolutePath() + " already exists.");
            } else {
                Path path = Paths.get(filePath);
                try {
                    Files.createDirectories(path.getParent());
                    Files.createFile(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(inputText);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else throw new RuntimeException("File path is not absolute");
    }
}
