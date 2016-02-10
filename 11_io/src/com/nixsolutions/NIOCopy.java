package com.nixsolutions;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by pantiukhin on 2/8/2016. Using the java.nio package
 */
public class NIOCopy extends SimpleFileVisitor<Path> {
    private final Path targetPath;
    private Path sourcePath = null;

    public NIOCopy(Path targetPath) {
        this.targetPath = targetPath;
    }

    public static void main(String[] args) throws IOException {
        Path sourcePath = Paths.get("Foder_Structure");
        Path targetPath = Paths.get("Foder_Structure_copy");
        Files.walkFileTree(sourcePath, new NIOCopy(targetPath));
    }

    @Override
    public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
        if (sourcePath == null) {
            sourcePath = dir;
        } else {
            Files.createDirectories(targetPath.resolve(sourcePath.relativize(dir)));
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        Files.copy(file, targetPath.resolve(sourcePath.relativize(file)));
        return FileVisitResult.CONTINUE;
    }
}

