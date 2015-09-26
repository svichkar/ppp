package com.nixsolutions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

public class DateOne {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter a year.");
			printMonthLengthByYear(Integer.parseInt(br.readLine()));
		} catch (Exception ex) {
			System.out.println("Invalid year. Please start again.");
		}
	}
	
	private static void printMonthLengthByYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		System.out.printf("Lengths of months for selected year. Year: %s.%n", year);
		for (int i = 0; i < 12; i++) {
			cal.set(Calendar.MONTH, i);
			System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
	}
}
