package com.nixsolutions;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.PatternSyntaxException;

/**
 * Created by sobolenko on 2/4/2016.
 */

public class FileCopy {
    public List<File> result = new ArrayList<File>();
    Random random = new Random();

    /**
     * recursively get all file names
     *
     * @param folder target folder
     * @return list of paths
     */
    public List<File> getAllFilesFromDirectoryIO(File folder) {
        try {
            for (File entryFiles : folder.listFiles()) {
                if (entryFiles.isDirectory()) {
                    getAllFilesFromDirectoryIO(entryFiles);
                }
                result.add(entryFiles);
            }
        } catch (NullPointerException ne) {
            ne.getStackTrace();
        }
        return result;
    }

    /**
     * Create 10 random directories according task
     *
     * @throws IOException
     */
    public void createDirectories() throws IOException {
        List<String> directoriesPaths = new ArrayList<String>();
        String workDirectory = System.getProperty("user.dir");
        Path resulPath = null;
        Path resulFileName = null;
        for (int capacity = 0; capacity < 10; capacity++) {
            String initialPath = generateRandomDirPath("");
            directoriesPaths.add(initialPath);
            for (int i = 0; i < random.nextInt(1) + 2; i++) {
                String[] splitPath = initialPath.split("\\u005c");
                String concatPath = "";
                for (int j = 0; j < random.nextInt(splitPath.length) + 1; j++) {
                    concatPath += splitPath[j] + File.separator;
                }
                String assemblyPath = generateRandomDirPath(concatPath);
                directoriesPaths.add(assemblyPath);
            }
        }
        for (String str : directoriesPaths) {   //creating files with random content in directories
            resulPath = Paths.get(workDirectory + File.separator + "testDir" + File.separator + str);
            resulFileName = Paths.get(resulPath + File.separator + generateRamdomString(3) + "." + generateRamdomString(3));
            Files.createDirectories(resulPath);
            Files.createFile(resulFileName);
            FileOutputStream fos = new FileOutputStream(resulFileName.toFile());
            fos.write(generateRamdomString(10000).getBytes());
            fos.close();
            fos.flush();
        }
    }

    /**
     * generate random paths
     *
     * @param point base point (directory) from where paths will be generated
     * @return string with path
     */
    public String generateRandomDirPath(String point) {
        String dirName = "";
        String path = point;
        for (int i = 0; i < random.nextInt(5) + 3; i++) {
            dirName += (char) (random.nextInt(25) + 97);
        }
        path += dirName + File.separator;
        try {
            if (path.split(File.separator).length < random.nextInt(6) + 3) {
                point = generateRandomDirPath(path);
            }
        } catch (PatternSyntaxException pse) {
            if (path.split("\\u005c").length < random.nextInt(6) + 3) {
                point = generateRandomDirPath(path);
            }
        }
        return point;
    }

    /**
     * used for generate random file names and content
     *
     * @param count length of string
     * @return string
     */
    public String generateRamdomString(int count) {
        String result = "";
        for (int i = 0; i < random.nextInt(count) + 2; i++) {
            result += (char) (random.nextInt(25) + 97);
        }
        return result;
    }

    public FileVisitResult getAllFilesFromDirectoryNIO(final Path folder, final Path destDir) throws IOException {

        Files.walkFileTree(folder, new FileVisitor<Path>() {
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path newd = destDir.resolve(folder.relativize(dir));
                if (!Files.exists(newd.getParent())) {
                    Files.createDirectory(newd.getParent());
                }
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path newd = destDir.resolve(folder.relativize(file));
                if (!Files.exists(newd.getParent())) {
                    Files.createDirectory(newd.getParent());
                }
                Files.copy(file, newd, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        return FileVisitResult.CONTINUE;
    }

    public void makeCopyWithJavaIo(String srcFolder, String destFolder) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        File destDirectiryOrFile = null;
        File destFile = new File(destFolder);
        File srcFile = new File(srcFolder);
        List<File> allFilesInDir = getAllFilesFromDirectoryIO(srcFile);
        for (File file : allFilesInDir) {
            destDirectiryOrFile = new File(destFile.toString() + file.getPath().replace(srcFolder, ""));
            if (!destDirectiryOrFile.getParentFile().exists()) {
                destDirectiryOrFile.getParentFile().mkdirs();
            }
            if (file.isFile()) {
                fis = new FileInputStream(file);
                fos = new FileOutputStream(destDirectiryOrFile);
                while ((length = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                fos.flush();
                fos.close();
            }
        }
    }

    public void makeCopyWithJavaNio(String srcFolder, String destFolder) throws IOException {
        Path sourceFolder = Paths.get(srcFolder);
        Path destDir = Paths.get(destFolder);
        getAllFilesFromDirectoryNIO(sourceFolder, destDir);
    }

    public void makeCopyWithApacheCommonsIo(String srcFolder, String destFolder) throws IOException {
        File destDir = new File(destFolder);
        File srcDir = new File(srcFolder);
        FileUtils.copyDirectory(srcDir, destDir);
    }

}

