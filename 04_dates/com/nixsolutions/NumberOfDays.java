package com.nixsolutions;

import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class NumberOfDays {

	public static void main(String[] args) {
		NumberOfDays days = new NumberOfDays();
		int year = new NumberOfDays().getYearFromConsole();
		if (year > 0) {
			days.countNumberOfDaysInMonth(year);
		} else {
			System.out.println("You put incorrect year. Please rerun this program");
		}
	}

	

	/**
	 * Used for printing the number of the days in each of the month in some
	 * year
	 * 
	 * @param integer
	 *            number of year
	 * @return the list of the month and number of the days in each of them to
	 *         console*
	 */
	public void countNumberOfDaysInMonth(int year) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		System.out.println("You asked for " + year + " year");
		for (int i = 0; i < 12; i++) {
			calendar.set(Calendar.MONTH, i);
			System.out.println(Month.of(i + 1) + ": " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
	}

	/**
	 * Used for retrieving the year from keyboard
	 * 
	 * @returns integer number of year typed from keyboard. In the error case it
	 *          returns -1
	 */
	private int getYearFromConsole() {
		int i = -1;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please put the year that you are interesting in");
		String input = scanner.nextLine();
		try {
			i = Integer.parseInt(input);
			return i;
		} catch (Exception ex) {
			return i;
		} finally {
			scanner.close();
		}
	}
}
