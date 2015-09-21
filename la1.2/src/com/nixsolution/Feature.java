package com.nixsolution;

import java.util.Scanner;

public class Feature {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean isExit = false;
		while (!isExit) {
			String digitValue = "";
			System.out.print("Enter double value in normal or exponential form:");
			Scanner in = new Scanner(System.in);
			//enter string
			digitValue = in.nextLine();
			in.close();
			try {
				if (digitValue.contains("E") || digitValue.contains("e")) {
					System.out.print("In double format:");
					System.out.print(ExponentialConverter.convertExpToDouble(digitValue + "d"));
				} else {
					System.out.print("In exponential format:");
					System.out.print(ExponentialConverter.convertDoubleToExp(digitValue + "d"));
				}
			} catch (Exception e) {
				System.out.print("Incorrect format");
			}
			System.out.print("Do you want to exit? Y/N:");
			String userSelection = in.nextLine();
			in.close();
			if (userSelection.equalsIgnoreCase("y"))
				isExit = true;

		}
	}
}
