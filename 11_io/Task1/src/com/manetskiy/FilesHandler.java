package com.manetskiy;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesHandler {

    public void copyIO(File source, File target) throws IOException {
        if (source.isDirectory()) {
            if (!target.exists())
                target.mkdir();

            File[] fileNames = source.listFiles();
            for (File fileName : fileNames) {
                File src = new File(source, fileName.getName());
                File trgt = new File(target, fileName.getName());
                copyIO(src, trgt);
            }
        } else {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new FileInputStream(source);
                out = new FileOutputStream(target);
                int length;
                byte[] buffer = new byte[256];
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }finally {
                if (in != null) in.close();
                if (out != null) out.close();
            }

        }
    }

    public void copyNIO(File source, File target) throws IOException {
        CopyWithNIO printFiles = new CopyWithNIO(target.toPath());
        Files.walkFileTree(Paths.get(source.getName()), printFiles);
    }

    private class CopyWithNIO extends SimpleFileVisitor<Path> {
        private Path target;
        private Path source = null;

        public CopyWithNIO(Path target) {
            this.target = target;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (source == null) {
                source = dir;
            } else {
                Files.createDirectories(target.resolve(source.relativize(dir)));
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.copy(file, target.resolve(source.relativize(file)));
            return FileVisitResult.CONTINUE;
        }
    }

    public void copyFileUtils(File source, File target) throws IOException {
        FileUtils.copyDirectory(source, target);
    }
}
