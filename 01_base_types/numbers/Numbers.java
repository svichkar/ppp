package numbers;

import java.util.Scanner;

public class Numbers {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String userNumber;
		double value;

		System.out.print("Provide number: ");
		userNumber = scan.nextLine();

		try {

			value = Double.parseDouble(userNumber);
			if ((userNumber.indexOf('e') != -1) || (userNumber.indexOf('E') != -1)) {

				System.out.println(String.format("%.2f", value));

			}

			else {

				System.out.println(String.format("%e", value));
			}
		}

		catch (java.lang.NumberFormatException e) {

			System.out.println("Incorrect format");

		}

	}

}
