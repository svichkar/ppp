package com.nixsolutions;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

import org.apache.commons.io.*;

public class DeepCopy {

    public static void main(String[] argc) {
        String rootFolder = "rootFolder";
        String oldAbsolutePath = System.getProperty("user.dir") + "\\" + rootFolder;
        String newAbsolutePath = System.getProperty("user.dir") + "\\newFolder";

        try (Scanner scan = new Scanner(System.in)) {
            while (true) {
                System.out.println("Please choose:");
                System.out.println("0. Exit(0)");
                System.out.println("1. Create preparation hierarchy");
                System.out.println("2. Remove both folder (old and new)");
                System.out.println("3. Copy with IO");
                System.out.println("4. Copy with NIO");
                System.out.println("5. Copy with Apache commons IO");
                switch (scan.nextInt()) {
                    case 0: {
                        System.exit(0);
                        break;
                    }
                    case 1: {
                        preparation(rootFolder);
                        break;
                    }
                    case 2: {
                        FileUtils.deleteDirectory(new File(oldAbsolutePath));
                        FileUtils.deleteDirectory(new File(newAbsolutePath));
                        break;
                    }
                    case 3: {
                        copyWithIO(oldAbsolutePath, newAbsolutePath);
                        break;
                    }
                    case 4: {
                        copyWithNIO(oldAbsolutePath, newAbsolutePath);
                        break;
                    }
                    case 5: {
                        copyWithApache(oldAbsolutePath, newAbsolutePath);
                        break;
                    }
                    default: {
                        System.out.println("Choose another number.");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyWithIO(String oldAbsolutePath, String newAbsolutePath) throws IOException {
        File oldFolder = new File(oldAbsolutePath);
        File newFolder = new File(newAbsolutePath);
        if (oldFolder.exists()) {
            if (oldFolder.isDirectory()) {

                //if directory not exists, create it
                if (!newFolder.exists()) {
                    newFolder.mkdir();
                }
                //list all the directory contents
                String files[] = oldFolder.list();

                for (String file : files) {
                    //recursive copy
                    copyWithIO(new File(oldFolder, file).getAbsolutePath(),
                            new File(newFolder, file).getAbsolutePath());
                }
            } else {
                //if file, then copy it
                try (InputStream in = new FileInputStream(oldFolder);
                     OutputStream out = new FileOutputStream(newFolder)) {
                    byte[] buffer = new byte[1024];

                    int length;
                    //copy the file content in bytes
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                } catch (IOException e) {
                    throw e;
                }
            }
        } else {
            System.out.println("Directory does not exist.");
        }
    }

    public static void copyWithNIO(String oldAbsolutePath, String newAbsolutePath) throws IOException {
        final Path source = Paths.get(oldAbsolutePath);
        final Path target = Paths.get(newAbsolutePath);
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes)
                    throws IOException {
                Files.copy(file, target.resolve(source.relativize(file)));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path directory,
                                                     BasicFileAttributes attributes) throws IOException {
                Path targetDirectory = target.resolve(source.relativize(directory));
                try {
                    Files.copy(directory, targetDirectory);
                } catch (FileAlreadyExistsException e) {
                    if (!Files.isDirectory(targetDirectory)) {
                        throw e;
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void copyWithApache(String oldAbsolutePath, String newAbsolutePath) throws IOException {
        FileUtils.copyDirectoryToDirectory(new File(oldAbsolutePath), new File(newAbsolutePath));
    }

    private static void preparation(String root) throws IOException {
        File f = new File(System.getProperty("user.dir") + "\\" + root);
        f.mkdir();
        String currLocation = f.getAbsolutePath();

        for (int i = 0; i < 10; i++) { //number of different folder
            String path = currLocation;
            for (int j = 1; j < i + 4; j++) { //level deep in each folder
                path += "\\folder" + new String(new char[j]).replace("\0", i + "");
            }
            path += "\\" + i + ".txt";
            createHierarchy(path);
        }
    }

    private static void createHierarchy(String path) throws IOException {
        File file = new File(path);
        new File(file.getParent()).mkdirs();
        file.createNewFile();
    }

}
