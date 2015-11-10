package com.nixsolutions.Arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.Formatter;

public class SecondExercise {

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
				String inputNumberUp = inputNumber.toUpperCase();
				String[] breakForMantissaAndexponent = inputNumberUp.split("E");
				int exponent = Integer.parseInt(breakForMantissaAndexponent[1]);
				double mantissa = Double.parseDouble(breakForMantissaAndexponent[0]);
				double resultNumber = (double) (mantissa * (Math.pow(10, exponent)));
				System.out.println("Result number (double): " + resultNumber);
			} else {
				String[] breakForMantissaAndexponent = inputNumber.split("\\.");
				int digit = breakForMantissaAndexponent[0].length();
				int quantity = breakForMantissaAndexponent[0].length() + breakForMantissaAndexponent[1].length();
				double mantissa = Double.parseDouble(inputNumber) / (Math.pow(10, digit - 1));
				Formatter formatter = new Formatter();
				System.out.println(
						"Result number (exp): " + formatter.format("%" + quantity + "e" + (digit - 1), mantissa));
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
