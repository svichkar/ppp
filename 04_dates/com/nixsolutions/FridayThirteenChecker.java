package com.nixsolutions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class FridayThirteenChecker {

	public static void main(String[] args) {

		System.out.println("Please enter a date in MM-DD-YYYY format");
		Scanner in = new Scanner(System.in);
		String inputDate = in.nextLine();
		in.close();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		try {
			Date date = dateFormat.parse(inputDate);
			new FridayThirteenChecker().isFridayThirteen(date);
		} catch (ParseException ex) {
			System.out.println("You put data in ivalid format.");
		}

	}
/**
 * @param incoming date by user
 * @return message if the date is Friday 13th or not
 * */
	private void isFridayThirteen(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_MONTH) == 13) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				System.out.println("The date that you asked is Friday, 13. Be scared :)");
			} else {
				System.out.println("The date that you asked is not Friday thirteen");
			}
		} else {
			System.out.println("The date that you asked is not 13");
		}
	}
}
