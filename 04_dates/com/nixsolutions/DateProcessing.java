package com.nixsolutions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Class was created for processing dates
 * 
 * @author maxb
 * 
 */
public class DateProcessing {

	/**
	 * Entry point under which we execute all implemented methods
	 * 
	 * @param args
	 *            Arguments that required for main method
	 * @throws ParseException
	 *             Throwing exceptions in case parsing string that contains date
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws ParseException,
			InterruptedException {

		String userData = "";

		while (!userData.contains("q")) {

			// //asking user for an action
			System.out
					.println("Select method that you want to run (type 'q' to exit):");
			System.out
					.println("1. Show amount of days for each month. You need to define a year");
			System.out
					.println("2. Show mondays for particular month and year. You need to define month and year");
			System.out
					.println("3. Check that a date is 13th Friday. You need to define a date");
			System.out
					.println("4. Show how much time has passed since a particular date. You need to define a date");
			System.out
					.println("5. Show the current date for locale Canada, Germany, Vietnam, Pakistan");

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				userData = br.readLine();
			} catch (Exception ex) {
				System.out.println(String.format("I/O exception. $s",
						ex.getMessage()));
			}

			String patternForNumber = "^[1-5]+$";
			Pattern ptrnNumber = Pattern.compile(patternForNumber);

			if (ptrnNumber.matcher(userData).matches()) {
				switch (Integer.valueOf(userData)) {
				case 1:
					System.out
							.println("Please, define year in the following format '2000'");
					String userYear = "";

					try {
						BufferedReader br = new BufferedReader(
								new InputStreamReader(System.in));
						userYear = br.readLine();

						showDurationOfEachMonth(userYear);
					} catch (Exception ex) {
						System.out.printf(
								"Cannot process user-defined data %1s%n",
								ex.getMessage());
					}
					Thread.sleep(5000);
					System.out.println();
					break;
				case 2:
					System.out
							.println("Please, define year and month in the following format '2000-01'");
					String uYearMonth = "";
					String uYear = "";
					String uMonth = "";

					try {
						BufferedReader br = new BufferedReader(
								new InputStreamReader(System.in));
						uYearMonth = br.readLine();
						String[] ym = uYearMonth.split("-");
						uYear = ym[0];
						uMonth = ym[1];

						showAllMondaysOfMonth(uYear, uMonth);
					} catch (Exception ex) {
						System.out.printf(
								"Cannot process user-defined data %1s%n",
								ex.getMessage());
					}
					Thread.sleep(5000);
					System.out.println();

					break;
				case 3:
					System.out
							.println("Please, define date in the following format '2000-01-01'");
					String dateOfLuckDay = "";

					try {
						BufferedReader br = new BufferedReader(
								new InputStreamReader(System.in));
						dateOfLuckDay = br.readLine();
						checkIsDateLuckyDay(dateOfLuckDay);
					} catch (Exception ex) {
						System.out.printf(
								"Cannot process user-defined data %1s%n",
								ex.getMessage());
					}
					Thread.sleep(5000);
					System.out.println();

					break;
				case 4:
					System.out
							.println("Please, define date in the following format '2000-01-01'");
					String dateInPast = "";

					try {
						BufferedReader br = new BufferedReader(
								new InputStreamReader(System.in));
						dateInPast = br.readLine();

						showHowMuchTimeHasPassed(dateInPast);
					} catch (Exception ex) {
						System.out.printf(
								"Cannot process user-defined data %1s%n",
								ex.getMessage());
					}
					Thread.sleep(5000);
					System.out.println();

					break;
				case 5:
					try {
						showDateForDiffLocale();
					} catch (Exception e) {
						System.out.printf(
								"Cannot process user-defined data %1s%n",
								e.getMessage());
					}
					Thread.sleep(3000);
					System.out.println();
					break;
				default:
					System.out.println("You just selected unexpected variant!");
					Thread.sleep(3000);
					System.out.println();

					break;
				}
			} else if (userData.compareTo("q") == 0) {
				System.out.println("Bye-bye. Exiting....");
			} else {
				System.out.println("Please define number in range 1-5");
			}

			// all mondays
			/*
			 * String userMonth = "9"; showAllMondaysOfMonth(userYear,
			 * userMonth); // Friday 13 String dateOfLuckDay = "2015-03-13";
			 * checkIsDateLuckyDay(dateOfLuckDay); // time... is time String
			 * dateInPast = "0001-02-12"; showHowMuchTimeHasPassed(dateInPast);
			 * // show date for diff locales
			 */
		}

	}

	/**
	 * Method for showing amount of days of each months
	 * 
	 * @param year
	 *            User-defined year
	 * @throws ParseException
	 *             Throwing exceptions in case parsing string that contains date
	 */
	public static void showDurationOfEachMonth(String year)
			throws ParseException {
		int amountOfMonthes = 12;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();

		for (int i = 1; i <= amountOfMonthes; i++) {
			String date = String.format("%1s-%2s-01", year, i);
			Date d1 = sdf.parse(date);
			c1.setTime(d1);
			System.out.printf("Month %1s has %2s days%n", c1.getDisplayName(
					Calendar.MONTH, Calendar.LONG, Locale.ENGLISH), c1
					.getActualMaximum(Calendar.DAY_OF_MONTH));
		}

	}

	/**
	 * Method for showing all mondays for particular month
	 * 
	 * @param year
	 *            User-defined year
	 * @param month
	 *            User-defined month
	 * @throws ParseException
	 *             Throwing exceptions in case parsing string that contains date
	 */
	public static void showAllMondaysOfMonth(String year, String month)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = String.format("%1s-%2s-01", year, month);
		Date d1 = sdf.parse(date);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		for (int i = 1; i <= c1.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				System.out.printf("This date %s is a Monday%n",
						sdf.format(c1.getTime()));
			}
			c1.add(Calendar.DATE, 1);
		}

	}

	/**
	 * Method for validating date is Friday 13th
	 * 
	 * @param userDate
	 *            User-defined date
	 * @throws ParseException
	 *             Throwing exceptions in case parsing string that contains date
	 */
	public static void checkIsDateLuckyDay(String userDate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = String.format(userDate);
		Date d1 = sdf.parse(date);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);

		if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
				& c1.get(Calendar.DAY_OF_MONTH) == 13) {
			System.out.printf("Yeah!! Date %1s is lucky day%n",
					sdf.format(c1.getTime()));
		} else {
			System.out.printf("Date %1s is usual day%n",
					sdf.format(c1.getTime()));
		}
	}

	/**
	 * Method for showing how much time has passed since defined date in past.
	 * 
	 * @param userDate
	 *            User-defined date in format yyyy-MM-dd. Future date won't be
	 *            processed
	 * @throws ParseException
	 *             Throwing exceptions in case parsing string that contains date
	 */
	public static void showHowMuchTimeHasPassed(String userDate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = String.format(userDate);
		Date d1 = sdf.parse(date);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();

		if (c1.before(c2)) {

			int diffYears;
			int diffMonths;
			int diffDays;
			double remain;

			long diff = 0;
			while (c1.before(c2)) {
				c1.add(Calendar.DAY_OF_MONTH, 1);
				diff++;
			}

			diffYears = (int) Math.floor(diff / 365);
			diffMonths = (int) Math.floor((diff - (diffYears * 365)) / 30);
			diffDays = (int) diff - (diffMonths * 30) - (diffYears * 365);

			System.out
					.printf("Time has passed since date \"%1s\" is %2s year(s) %3s month(s) %4s day(s)%n",
							sdf.format(c1.getTime()), diffYears, diffMonths,
							diffDays);
		} else {
			System.out.printf("Date %1s is future date!",
					sdf.format(c1.getTime()));
		}

	}

	/**
	 * Method for showing the current date for different locales
	 * 
	 */
	public static void showDateForDiffLocale() {
		Date today = new Date();
		DateFormat formatCanada = DateFormat.getDateInstance(DateFormat.FULL,
				Locale.CANADA);
		System.out.printf("Canada - %1s%n", formatCanada.format(today));
		DateFormat formatGermany = DateFormat.getDateInstance(DateFormat.FULL,
				Locale.GERMAN);
		System.out.printf("Germany - %1s%n", formatGermany.format(today));
		DateFormat formatVietnam = DateFormat.getDateInstance(DateFormat.FULL,
				new Locale("vi", "VN"));
		System.out.printf("Vietnam - %1s%n", formatVietnam.format(today));
		// /as Pakistan is very restricted contry we use Indian as locale for it
		// (hi-IN or en-IN)
		DateFormat formatPakistan = DateFormat.getDateInstance(DateFormat.FULL,
				new Locale("en", "PK"));
		System.out.printf("Pakistan - %1s%n", formatPakistan.format(today));
	}

}
