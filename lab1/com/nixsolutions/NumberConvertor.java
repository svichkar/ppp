package com.nixsolutions;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.regex.Pattern;
import java.math.*;

public class NumberConvertor {

	public static void main(String[] args) {

		String userData = "";
		while (!userData.contains("q")) {
			System.out.println("Enter a number('q' for exit):");

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				userData = br.readLine();
			} catch (Exception ex) {
				System.out.println(String.format("I/O exception. $s",
						ex.getMessage()));
			}

			String regexWithExponential = "^[-+]?[0-9]*[.,]?[0-9]*[eE]+[-+]?[0-9]{1,}?$";
			String regexWithoutExponential = "^[-+]?[0-9]*[.,]?[0-9]*([0-9]+)?$";
			Pattern patternWithExponential = Pattern
					.compile(regexWithExponential);
			Pattern patternWithoutExponential = Pattern
					.compile(regexWithoutExponential);

			if (patternWithExponential.matcher(userData).matches()) {
				System.out.println(String.format("Typed number is %.2f",
						Float.parseFloat(userData)));
			} else if (patternWithoutExponential.matcher(userData).matches()) {
				System.out.println(String.format("Typed number is %.2E",
						Float.parseFloat(userData)));
			} else if (userData.trim().equalsIgnoreCase("q")) {
				System.out.println("Bye-bye!");
			} else {
				System.out.println(String.format("Wrong data was typed - %s",
						userData));
			}
		}

	}
}
