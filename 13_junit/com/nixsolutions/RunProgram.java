package com.nixsolutions;

import java.io.File;
import java.io.IOException;

public class RunProgram {

	public static void main(String[] args) throws IOException {
		Robot rb = new Robot();
		Program pr = new Program(rb);
		rb.setFolderForLog(new File("D:\\WorkPlace\\"));
		pr.executeProgram("lffrflfrrffff");
	}

}
