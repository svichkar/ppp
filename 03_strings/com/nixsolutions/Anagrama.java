package com.nixsolutions;

import java.util.Arrays;
import java.util.Scanner;

public class Anagrama {

	public static void main(String[] args) {	
		Scanner in = new Scanner(System.in);
		String userInput1, userInput2 = "";
		
		System.out.println("Please put the fist value and press Enter.");
		userInput1 = in.nextLine();

		System.out.println("Please put the second value and press Enter.");
		userInput2 = in.nextLine();

		char[] input1 = userInput1.replaceAll("[^ A-Za-z0-9]", "").replace(" ", "").toLowerCase().trim().toCharArray();
		Arrays.sort(input1);
		char[] input2 = userInput2.replaceAll("[^ A-Za-z0-9]", "").replace(" ", "").toLowerCase().trim().toCharArray();
		Arrays.sort(input2);

		System.out.println(Arrays.equals(input1, input2) ? "These strings are anagrams of each other"
				: "These strings are not anagrams of each other");

		in.close();
	}
}
