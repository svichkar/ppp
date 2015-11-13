package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Abbreviation {

	public static String madeAbbreviationForName(String fullName) {
		String[] splitedFullName = new String[3];
		String abbFirstName = "";
		String abbSecondName = "";
		String abbFatherName = "";
		splitedFullName = fullName.split(" ");
		for (int i = 0; i < 1; i++) {
			abbFirstName = splitedFullName[i].subSequence(0, 1).toString();
			abbSecondName = splitedFullName[i + 1].subSequence(0, 1).toString();
			abbFatherName = splitedFullName[i + 2].subSequence(0, 1).toString();
		}
		String completeAbb = abbFirstName + abbSecondName + abbFatherName;
		return fullName + " = " + completeAbb;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter full name: ");
		String inputName = reader.readLine();
		String result = madeAbbreviationForName(inputName);
		System.out.println(result);
	}

}
