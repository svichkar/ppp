package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exponent {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String inputNumber;
		boolean qualityInputNumber = true;
		while (qualityInputNumber) {
			System.out.print("Please enter a number: ");
			inputNumber = reader.readLine();
			while (true) {
				try {
					Double checkNumber = Double.parseDouble(inputNumber);
					break;
				} catch (NumberFormatException ignore) {
					System.out.print("Invalid input number. Please enter the valid number: ");
					inputNumber = reader.readLine();
				}
			}
			if (inputNumber.contains("E") || inputNumber.contains("e")) {
				System.out.printf("Result number (double): %f\n", Double.valueOf(inputNumber));
			} else {
				System.out.printf("Result number (exp): %E\n", Double.valueOf(inputNumber));
			}
			System.out.print("Would you like continue to enter numbers? Y/N ");
			String yesNo = "";
			yesNo = reader.readLine();
			if (yesNo.toUpperCase().equals("N"))
				qualityInputNumber = false;
			else if (yesNo.toUpperCase().equals("Y"))
				qualityInputNumber = true;
			else
				qualityInputNumber = false;

		}

	}

}
