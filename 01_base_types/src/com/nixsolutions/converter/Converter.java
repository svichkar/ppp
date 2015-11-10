package com.nixsolutions.converter;

import java.text.*;
import java.util.Scanner;

public class Converter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Converter().converter();
	}

	public void converter() {
		boolean isRun = true; // Flag of running program
		Scanner input = new Scanner(System.in);
		while (isRun) {
			try {
				System.out.println("Please put here the value in floating pointer format to convert it in exponential one and press Enter");
				String strInput = input.nextLine();
				//Used for determining the way of conversion
				if (strInput.toUpperCase().contains("E")) {
					expToDoubleConversion(strInput);
				}else {
					doubleToExpConversion(strInput);
				}
				isRun = exitFromProgram(input);
			} catch (Exception ex) {
				// It returns an error message about incorrect format of
				// incoming data. Proposes to leave the program
				System.out.println("The value you just entered is neither exponential nor normal format of number.");
				isRun = exitFromProgram(input);
			}
		}
		// Close scanner
		input.close();
	}

	/**
	 * Converts normal value to exponential
	 * @param -input data in normal format
	 * @return - prints to command line the result of conversion.
	 */
	private void doubleToExpConversion(String strInput) {

		double dblInput = Double.parseDouble(strInput);
		NumberFormat formatter = new DecimalFormat("0.#####E0");
		System.out.println("After conversion your value is: " + formatter.format(dblInput));
	}

	/**
	 * Converts exponential value to normal
	 * @param -input data in exponential format
	 * @return - prints to command line the result of conversion.
	 */
	private void expToDoubleConversion(String exponential) {
		Double out = Double.parseDouble(exponential);
		System.out.printf("After conversion your value is: %.4f", out);
		System.out.println();
	}

	/**
	 * Method for either continue working with program or to close it
	 * @param input current scanner instance
	 * @return boolean true or false
	 */
	private boolean exitFromProgram(Scanner input) {
		System.out.println("Do you want to stop the program? (y / n)");
		String strAnswer = input.nextLine();
		return (!strAnswer.toUpperCase().equals("Y"));
	}
}
