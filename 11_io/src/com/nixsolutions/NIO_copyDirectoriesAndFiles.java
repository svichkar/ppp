package com.nixsolutions;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sirotkin Mikhail
 */
public class NIO_copyDirectoriesAndFiles {

    public static void main(String[] args) throws IOException{
        copyDirectoryUsingNIO("E:\\timeReports\\2015\\timesheets", "E:\\timeReports\\2015\\2");
    }

    /**
     * Static method that copy folder with inside structure of directories and files to new place
     * If directory already exists we just use it for copy files to them
     * If files with the same names in the same directory will be found they will be re-written
     * @param src - path of source directory
     * @param dist - path of distination directory.
     * @throws IOException
     */
    public static void copyDirectoryUsingNIO(String src, String dist) throws IOException{
        Path distPath = Paths.get(dist);
        if(!Files.exists(distPath))
            Files.createDirectory(distPath);
        List<String> pathes = new LinkedList<>();
        Path srcPath = Paths.get(src);
        Files.walk(srcPath).forEach(wholePath -> pathes.add(wholePath.toString()));
        for (String wholePath : pathes) {
            String pathForCopy = wholePath.replace(src, dist);
            if (Files.isDirectory(Paths.get(wholePath), LinkOption.NOFOLLOW_LINKS))
                Files.createDirectories((Paths.get(pathForCopy)));
            else
                Files.copy(Paths.get(wholePath), Paths.get(pathForCopy), StandardCopyOption.REPLACE_EXISTING);
        }

    }

}
