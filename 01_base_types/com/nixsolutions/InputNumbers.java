package com.nixsolutions;

import java.util.Scanner;

public class InputNumbers {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String userInput = "go";

		while (!userInput.equals("exit")) {
			System.out.println("type some double values or type exit");
			userInput = scan.nextLine();

			if (userInput.equals("exit")) {
				userInput = "exit";
				System.out.println("bye-bye");
			} else {
				try {
					if (userInput.toLowerCase().contains("e")) {
						System.out.printf("here is the value: %f \n", Double.valueOf(userInput.replace(",", ".")));
					} else {
						System.out.printf("here is the value: %e \n", Double.valueOf(userInput.replace(",", ".")));
					}
				} catch (NumberFormatException e) {
					System.out.println("this is not a number, please try again");
				}
			}
		}
		scan.close();
	}
}
