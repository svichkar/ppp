package com.nixsolutions.exceptions;

import java.util.Scanner;

public class Main {


	public static void main(String[] args) {
		
		SaveFile saveFile = new SaveFile();
		Scanner scn = new Scanner(System.in);
		System.out.println("Please put text you want to save and press Enter");
		
		
		try {
			//saveFile.save(strTextToSave, new File(".").getAbsolutePath()+"\\sting.txt");
			saveFile.save(scn.nextLine(),"D:\\java\\projects\\javappp\\05_exceptions\\test.txt");
			System.out.println("Text was succesfully saved");
		} catch (Exception ex) {
			System.out.println("Your code caused some error: " + ex.getMessage());
		}finally{
			scn.close();
		}
	}

}
