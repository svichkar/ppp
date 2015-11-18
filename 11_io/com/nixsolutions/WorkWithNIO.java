package com.nixsolutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rybkinrolla on 18.11.2015.
 */
public class WorkWithNIO {
    public static void main(String[] args){
        try {
            copyFolderStructureWithFiles("D:\\Test", "C:\\TestResult\\CopiedHere");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFolderStructureWithFiles(String source, String destination) throws IOException {
        Path sourcePath = Paths.get(source);
        List<Path> listOfPaths = new LinkedList<>();
        Files.walk(sourcePath).forEach(absolutePath -> listOfPaths.add(absolutePath));
        for (Path absolutePath : listOfPaths) {
            String newPath = absolutePath.toString().replace(source, destination);
            Files.createDirectories((Paths.get(newPath).getParent()));
            Files.copy(absolutePath, Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
        }

    }

}
