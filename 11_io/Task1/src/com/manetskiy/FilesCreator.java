package com.manetskiy;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Random;


public class FilesCreator {

    private String rootDirectory;

    public FilesCreator(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void createFiles() throws IOException {
        FilesWriter fw = new FilesWriter();
        Path path = createFoldersStructure();
        Files.walkFileTree(path, fw);
    }

    /*
    Internal method that creates one root folder, 10 folders therein,
    between 3 and 6 subfolders in each folder and one text file in
    each farther subfolder.
     */
    private Path createFoldersStructure() throws IOException {
        Random rand = new Random();
        String folder = "folder";
        String subfolder = "subfolder";
        String filename = "sometext.txt";
        String s = File.separator;

        //creating 10 folders
        for (int i = 1; i <= 10; i++) {
            StringBuilder tmp = new StringBuilder();
            tmp.append(rootDirectory).append(s).append(folder).append(i).append(s);
            int randFoldrNumbr = (rand.nextInt((6 - 3) + 1) + 3);
            //creating subfolders between 3 and 6
            for (int j = 1; j <= randFoldrNumbr; j++) {
                tmp.append(subfolder).append(j).append(s);
            }
            //create file
            tmp.append(i).append(filename);
            Path path = Paths.get(tmp.toString());
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        return Paths.get(rootDirectory);
    }

    /*
    Internal class for filling each file by random long value.
     */
    private class FilesWriter extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Random rand = new Random();
            Files.write(file, String.valueOf(rand.nextLong()).getBytes());
            return FileVisitResult.CONTINUE;
        }
    }
}
