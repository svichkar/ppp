package com.nixsolutions;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.math.*;

public class NumberConvertor {

	public static void main(String[] args) {

		String userData = "";
		while (!userData.contains("exit")) {
			System.out.println("Enter a number:");

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				userData = br.readLine();
			} catch (Exception ex) {
				System.out.println(String.format("I/O exception. $s",
						ex.getMessage()));
			}

			if (isNum(userData)) {
				if (userData.toLowerCase().contains("e")) {
					System.out.println(String.format("Typed number is %.2f",
							stringToNumber(userData)));
				} else {
					System.out.println(String.format("Typed number is %.2E",
							stringToNumber(userData)));
				}

			} else if (userData.contains("exit")) {
				System.out.println("Bye-bye!");
			} else {
				System.out.println(String.format("Wrong data was typed - %s",
						userData));
			}
		}

	}

	public static boolean isNum(String inputData) {

		return inputData.matches("^[-+]?[0-9]*.?[0-9]+([eE][-+]?[0-9]+)?$");
	}

	public static double stringToNumber(String inputData) {
		double result;
		try {
			result = Double.parseDouble(inputData);
		} catch (Exception ex) {
			result = 0;
		}
		return result;
	}

}
