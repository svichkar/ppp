package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.io.FileUtils;

public class IoLab {

	static final String SOURCE_PATH = "D:\\copy_from";
	static final String DEST_PATH = "D:\\copy_to";

	public static void useFileUtils(String source, String destination) {
		try {
			File srcFile = new File(source);
			File destFile = new File(destination);
			FileUtils.copyDirectory(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void useNio(String source, String destination) {
		try {
			Path sourcePath = Paths.get(source);
			Path destPath = Paths.get(destination);
			Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs)
						throws IOException {
					Files.createDirectories(destPath.resolve(sourcePath.relativize(dir)));
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
					Files.copy(file, destPath.resolve(sourcePath.relativize(file)));
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void useIo(String source, String destination) {
		File srcFile = new File(source);
		File destFile = new File(destination);
		if (srcFile.isDirectory()) {
			if (!destFile.exists()) {
				destFile.mkdir();
			}
			String files[] = srcFile.list();
			for (String file : files) {
				useIo(srcFile + File.separator + file, destFile + File.separator + file);
			}
		} else {
			try (InputStream input = new FileInputStream(srcFile); OutputStream output = new FileOutputStream(destFile)) {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = input.read(buffer)) != -1) {
					output.write(buffer, 0, length);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		 useFileUtils(SOURCE_PATH, DEST_PATH);
		//useNio(SOURCE_PATH, DEST_PATH);
		//useIo(SOURCE_PATH, DEST_PATH);

	}
}
