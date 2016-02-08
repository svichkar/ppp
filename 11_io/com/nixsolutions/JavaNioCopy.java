package com.nixsolutions;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class JavaNioCopy extends SimpleFileVisitor<Path> {

    public static void copy(String s, String d) {
        Path src = Paths.get(s);
        Path dest = Paths.get(d);
        try {
            Files.walkFileTree(src, new JavaNioCopy(src, dest));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Path src;
    private Path dest;

    public JavaNioCopy(Path src, Path dest) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir,
            BasicFileAttributes attrs) {
        try {
            Path destPath = dest.resolve(src.relativize(dir));
            Files.copy(dir, destPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        try {
            Files.copy(file, dest.resolve(src.relativize(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

}
