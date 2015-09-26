package com.nixsolutions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateFour {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter a date.");
			System.out.println(
					getCurrentDifference(new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine())));
		} catch (Exception ex) {
			System.out.println("Invalid date. Please start again.");
		}
	}

	private static String getCurrentDifference(Date input) {
		Date currentDate = new Date();
		String resStr = "";
		if (input.before(currentDate)) {
			long difference = currentDate.getTime() - input.getTime();
			long days = TimeUnit.MILLISECONDS.toDays(difference);
			long months = TimeUnit.MILLISECONDS.toDays(difference) * 12 / 365 % 12;
			long years = TimeUnit.MILLISECONDS.toDays(difference) / 365;
			resStr = String.format("Elapsed - days: %1$s, months: %2$s, years: %3$s.", 
					days, months, years); 
		} else {
			System.out.
			println("Invalid input - date is bigger than current. Returning empty string.");
		}
		return resStr;
	}
}
