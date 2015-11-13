package com.nixsolutions;

import java.time.Month;
import java.util.Calendar;
import java.util.Scanner;

public class Mondays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mondays mondays = new Mondays();
		int year, month;

		Scanner in = new Scanner(System.in);
		System.out.println("Please put the year");
		String inYear = in.nextLine();
		System.out.println("Please enter the number of month");
		String inMonth = in.nextLine();
		try {
			year = Integer.parseInt(inYear);
			month = Integer.parseInt(inMonth);
			mondays.datesOfMondays(year, month - 1);

		} catch (Exception ex) {
			System.out.println("You wrote incorrect month or year. Please rerun program and be attentive");
		} finally {
			in.close();

		}

	}

	/**
	 * @param integer
	 *            values of year and month number
	 * @return the list of Mondays' dates in current year in current months
	 */
	private void datesOfMondays(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		System.out.printf("The list of Mondays in " + Month.of(month + 1) + " month of year " + year + "\n");
		for (int i = 1; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, i);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
				System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

		}
	}
}
