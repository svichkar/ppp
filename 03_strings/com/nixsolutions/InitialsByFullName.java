package com.nixsolutions;

import java.util.Scanner;

public class InitialsByFullName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Put your full name and press Enter.");
		String input = in.nextLine();
		System.out.println("For your full name " + input + " initials are: "
				+ new InitialsByFullName().initials(input));
		in.close();
	}

	private String initials(String input) {
		String result = "";
		String[] arrNames = input.split(" ");
		for (int i = 0; i < arrNames.length; i++) {
			result += arrNames[i].substring(0, 1);
		}
		return result;
	}
}
