package com.nixsolutions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateThree {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter a date.");
			System.out.println(
					isFridayThirteenth(new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine())));
		} catch (Exception ex) {
			System.out.println("Invalid date. Please start again.");
		}
	}
	
	private static Boolean isFridayThirteenth(Date input) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(input);		
		return cal.get(Calendar.DAY_OF_MONTH) == 13 &&
				cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}
}
