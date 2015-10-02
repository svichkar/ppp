package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import exception.Save;

public class NewException implements Save {

    public static void main(String[] args) throws MalformedURLException {

	NewException newEx = new NewException();
	newEx.save("This is data for input file ", "D:\\1\\1\\1\\1\\1.txt");

    }

    @Override
    public void save(String inputText, String filePath) {
	BufferedWriter writer = null;
	try {

	    File file = new File(filePath);

	    if (!file.exists()) {
		String directoryPath = filePath.substring(0, filePath.lastIndexOf('\\'));
		new File(directoryPath).mkdirs();

	    }

	    System.out.println(file.getCanonicalPath());

	    writer = new BufferedWriter(new FileWriter(file)); // if file is not
							       // exist it will
							       // be created ,
							       // otherwise it
							       // will be
							       // overwritten
	    writer.write(inputText);
	    System.out.println("Data: " + inputText + "\nWas successfully written into file.");

	} catch (FileNotFoundExp e) {

	    e.printStackTrace();

	} catch (IOException iOexept) {

	    iOexept.printStackTrace();
	} finally {
	    try {
		writer.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

    }

}
