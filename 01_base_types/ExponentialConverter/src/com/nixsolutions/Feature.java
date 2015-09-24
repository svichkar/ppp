package com.nixsolutions;

import java.util.Scanner;

public class Feature {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean isExit = false;
		Scanner in = new Scanner(System.in);
		while (!isExit) {
			String digitValue = "";
			System.out.print("Enter double value in normal or exponential form:");
			// enter string
			digitValue = in.nextLine();
			//in.close();

			if (digitValue.matches("-?\\d+([Ee.]-?\\d+)?")) {
				if (digitValue.contains("E") || digitValue.contains("e")) {
					System.out.print("In double format: ");
					System.out.print(ExponentialConverter.convertExpToDouble(digitValue + "d"));
				} else {
					System.out.print("In exponential format: ");
					System.out.print(ExponentialConverter.convertDoubleToExp(digitValue + "d"));
				}
			} else
				System.out.print("Incorrect format \n");

			System.out.print("\ntry one more time \n");
			System.out.print("Do you want to exit? Y/N: \n");
			String userSelection;
			userSelection = in.nextLine();
			if (userSelection.equalsIgnoreCase("y"))
				isExit = true;
		}
		in.close();

	}
}
