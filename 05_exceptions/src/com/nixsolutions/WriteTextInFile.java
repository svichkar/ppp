package com.nixsolutions;

import java.io.File;
import exception.Save;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTextInFile implements Save {

    @Override
    public void save(String text, String path) {
	File txtFile = new File(path);
	if (!txtFile.exists()) { // Check if file exists
	    try {
		File dirPath = new File(path.substring(0, path.lastIndexOf("\\")));
		dirPath.mkdirs();
		txtFile.createNewFile(); // create new file
		System.out.format("New file \"%s\" is created succesfully\n", path);
	    } catch (FileWriteExeption e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else {
	    System.out.format("File \"%s\" is already exist\n", path);
	}
	try (FileWriter fw = new FileWriter(txtFile)) {
	    fw.write(text); // write text into file
	    System.out.format("Text \"%s\" is written into file succesfully", text);
	} catch (FileWriteExeption e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
