package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.*;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Nio {

	/**
	 * @param args
	 * @param out
	 */
	public static void main(String[] args) {
		// source
		Path pSource = Paths.get(".\\").toAbsolutePath();
		// target
		Path tempFolder = Paths.get("D:\\temp\\1").toAbsolutePath();

		try {
			List<Path> lp = new ArrayList<>();
			lp.addAll(listFiles(pSource));
			System.out.printf("Amount of files in folder %1s is %2s files%n",
					pSource.normalize(), lp.size());

			int amountOfCopiedFiles = copyFiles(pSource, tempFolder);
			System.out.printf("%1s Files were copied to folder %2s%n",
					amountOfCopiedFiles, tempFolder);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	@SuppressWarnings("finally")
	private static List<Path> listFiles(Path path) {
		List<Path> lFiles = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (Files.isDirectory(entry)) {
					List<Path> deeper = listFiles(entry);
					lFiles.addAll(deeper);
				}
				lFiles.add(entry);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Collections.sort(lFiles);
			return lFiles;
		}
	}

	private static int copyFiles(Path sourceFolder, Path distFolder) {
		int counter = 0;
		// get files from sourceFolder
		List<Path> lFiles = listFiles(sourceFolder);
		// creating folders
		for (Path file : lFiles) {
			if (Files.isDirectory(file, LinkOption.NOFOLLOW_LINKS)) {
				Path newFolder = Paths.get(distFolder.toString(), sourceFolder
						.relativize(file).toString());
				try {
					if (!Files.exists(newFolder, LinkOption.NOFOLLOW_LINKS)) {
						Files.createDirectory(newFolder);

					}
					counter++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// copy files
		for (Path file : lFiles) {
			if (!Files.isDirectory(file, LinkOption.NOFOLLOW_LINKS)) {
				Path newFile = Paths.get(distFolder.toString(), sourceFolder
						.relativize(file).toString());
				try {
					Files.copy(file, newFile,
							StandardCopyOption.REPLACE_EXISTING);
					counter++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return counter;
	}

}
