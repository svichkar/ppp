package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Start {

    private static final String FROM = "D:\\1";
    private static final String TO = "D:\\5";

    public static void main(String[] args) {
	copy(new File(FROM), new File(TO));

    }

    public static void copy(File position, File dest) {

	if (!position.isDirectory()) {
	    try {
		Files.createDirectories(dest.toPath());
		Files.copy(position.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    File from;
	    File to;
	    String[] listOfFiles = position.list();
	    for (int i = 0; i < listOfFiles.length; i++) {
		from = new File(position, listOfFiles[i]);
		to = new File(dest, listOfFiles[i]);

		copy(from, to);

	    }
	    System.out.println("Ok");

	}
    }
}
