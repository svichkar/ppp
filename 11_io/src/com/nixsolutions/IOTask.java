package com.nixsolutions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class IOTask {
    public static void main(String[] args) {
	File src = new File("D:\\JavaProjects\\paryshev.a\\javappp\\11_io");
	File dest = new File("C:\\TestFolder");
	try {
	    FileUtils.copyDirectory(src, dest);
	    System.out.println(
		    "Content from \"D:\\JavaProjects\\paryshev.a\\javappp\\11_io\" is copied to \"C:\\TestFolder\"");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
