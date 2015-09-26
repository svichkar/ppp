package com.nixsolutions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

public class DateTwo {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter a year.");
			int year = Integer.parseInt(br.readLine());
			System.out.println("Please enter a month.");
			int month = Integer.parseInt(br.readLine());
			printMondays(year, month - 1);
		} catch (Exception ex) {
			System.out.println("Invalid value encountered. Please start again.");
		}
	}
	
	private static void printMondays(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		for (int i = 1; i < cal.getActualMaximum(Calendar.WEEK_OF_MONTH) + 1; i++) {
			cal.set(Calendar.WEEK_OF_MONTH, i);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			if (cal.get(Calendar.MONTH) != month) {
				cal.set(Calendar.MONTH, month);
			} else {
				System.out.println(cal.get(Calendar.DAY_OF_MONTH));
			}
		}
	}

}
