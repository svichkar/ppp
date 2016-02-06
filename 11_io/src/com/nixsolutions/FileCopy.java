package com.nixsolutions;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sobolenko on 2/4/2016.
 */

public class FileCopy {
    public List<File> result = new ArrayList<File>();
    Random random = new Random();

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

    public void createDirectories() throws IOException {
        List<String> directoriesPaths = new ArrayList<String>();
        String workDirectory = System.getProperty("user.dir");
        Path resulPath = null;
        Path resulFileName = null;
        for (int capacity = 0; capacity < 10; capacity++) {
            //
            String initialPath = generateRandomDirPath("");
            directoriesPaths.add(initialPath);
            //
            for (int i = 0; i < random.nextInt(1) + 2; i++) {
                String[] splitPath = initialPath.split("\\u005c");
                String concatPath = "";
                for (int j = 0; j < random.nextInt(splitPath.length) + 1; j++) {
                    concatPath += splitPath[j] + "\\";
                }
                String assemblyPath = generateRandomDirPath(concatPath);
                directoriesPaths.add(assemblyPath);
            }
        }
        for (String str : directoriesPaths) {
            resulPath = Paths.get(workDirectory + "\\testDir\\" + str);
            resulFileName = Paths.get(resulPath + "\\" + generateRamdomString(3) + "." + generateRamdomString(3));
            Files.createDirectories(resulPath);
            Files.createFile(resulFileName);
            FileOutputStream fos = new FileOutputStream(resulFileName.toFile());
            fos.write(generateRamdomString(10000).getBytes());
            fos.close();
            fos.flush();
        }
        //
    }

    public String generateRandomDirPath(String point) {
        String dirName = "";
        String path = point;
        for (int i = 0; i < random.nextInt(5) + 3; i++) {
            dirName += (char) (random.nextInt(25) + 97);
        }
        path += dirName + "\\";
        if (path.split("\\u005c").length < random.nextInt(6) + 3) {
            point = generateRandomDirPath(path);
        }
        return point;
    }

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
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        File destDirectiryOrFile = null;
        File destFile = new File(destFolder);
        File srcFile = new File(srcFolder);
        List<File> allFilesInDir = getAllFilesFromDirectoryIO(srcFile);
        for (File file : allFilesInDir) {
            destDirectiryOrFile = new File(destFile.toString() + file.getAbsolutePath().replaceAll("\\w:", ""));
            if (!destDirectiryOrFile.getParentFile().exists()) {
                destDirectiryOrFile.getParentFile().mkdirs();
            }
            if (file.isFile()) {
                fis = new FileInputStream(file);
                fos = new FileOutputStream(destDirectiryOrFile);
                isr = new InputStreamReader(fis, "UTF-8");
                br = new BufferedReader(isr);
                String line = "";
                while ((line = br.readLine()) != null) {
                    fos.write(line.getBytes());
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

