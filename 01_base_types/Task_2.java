package com.nixsolutions;

import java.util.Scanner;


public class Task_2 {

	public static void main(String[] args) {
		System.out.println("Please enter your number");
		Scanner in = new Scanner(System.in);
		String number = in.next();
		if (number.toLowerCase().contains("e"))
			System.out.printf("Normal format: %f", Double.valueOf(number));
		else
			System.out.printf("Exponential format: %e", Double.valueOf(number));
    	
		in.close();		

	}

}
