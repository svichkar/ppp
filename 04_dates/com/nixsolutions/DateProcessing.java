package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**Class was created for processing dates
 * 
 * @author maxb
 *
 */
public class DateProcessing {

	/**Entry point under which we execute all implemented methods
	 * 
	 * @param args Arguments that required for main method
	 * @throws ParseException Throwing exceptions in case parsing string that contains date
	 */
	public static void main(String[] args) throws ParseException {

		String userYear = "2015";
		//duration for each month
		showDurationOfEachMonth(userYear);
		//all mondays
		String userMonth = "9";
		showAllMondaysOfMonth(userYear, userMonth);
		//Friday 13
		String dateOfLuckDay = "2015-03-13";
		checkIsDateLuckyDay(dateOfLuckDay);
		//time... is time
		String dateInPast = "0001-02-12";
		showHowMuchTimeHasPassed(dateInPast);
		//show date for diff locales
		showDateForDiffLocale();

	}

	/**Method for showing amount of days of each months
	 * 
	 * @param year User-defined year
	 * @throws ParseException Throwing exceptions in case parsing string that contains date
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

	/**Method for showing all mondays for particular month
	 * 
	 * @param year User-defined year 
	 * @param month User-defined month
	 * @throws ParseException Throwing exceptions in case parsing string that contains date
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

	/**Method for validating date is Friday 13th
	 * 
	 * @param userDate User-defined date
	 * @throws ParseException Throwing exceptions in case parsing string that contains date
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

	/**Method for showing how much time has passed since defined date in past.
	 * 
	 * @param userDate User-defined date in format yyyy-MM-dd.  Future date won't be processed
	 * @throws ParseException Throwing exceptions in case parsing string that contains date
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

			long diffYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
			long diffMonths = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
			long diffDays = c2.get(Calendar.DAY_OF_MONTH)
					- c1.get(Calendar.DAY_OF_MONTH);

			System.out
					.printf("Time has passed since date \"%1s\" is %2s year(s) %3s month(s) %4s day(s)",
							sdf.format(c1.getTime()), diffYears, diffMonths,
							diffDays);
		} else {
			System.out.printf("Date %1s is future date!",
					sdf.format(c1.getTime()));
		}

	}
	
	/**Method for showing the current date for different locales
	 * 
	 */
	public static void showDateForDiffLocale()
	{
		Date today = new Date(); 
		DateFormat formatCanada = DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA);
		System.out.printf("Canada - %1s%n", formatCanada.format(today));
		DateFormat formatGermany = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMAN);
		System.out.printf("Germany - %1s%n", formatGermany.format(today));
		DateFormat formatVietnam = DateFormat.getDateInstance(DateFormat.FULL, new Locale("vi", "VN"));
		System.out.printf("Vietnam - %1s%n", formatVietnam.format(today));
		///as Pakistan is very restricted contry we use Indian as locale for it (hi-IN or en-IN)
		DateFormat formatPakistan = DateFormat.getDateInstance(DateFormat.FULL, new Locale("en", "IN"));
		System.out.printf("Pakistan - %1s%n", formatPakistan.format(today));
	}
	
}
