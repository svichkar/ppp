package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WorkWithData {

	public static void lengthEachMonthsAtDesiredYear(int neededYear) {
		int dayOfMonth = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, neededYear);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		for (int i = 0; i < 12; i++) {
			calendar.set(Calendar.MONTH, i);
			dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
			System.out.println(String.format("%s: %s", monthName, dayOfMonth));
		}
	}

	public static void dateForAllMondays(int year, int month) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 1; i <= days; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, i);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				int date = calendar.get(Calendar.DAY_OF_MONTH);
				System.out.println(date);
			}

		}
	}

	public static void definitionFridayThirteenth(String neededDate) throws ParseException {
		String[] splitedData = new String[3];
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		Date date = sdf.parse(neededDate);
		calendar.setTime(date);
		splitedData = neededDate.split("-");
		if (splitedData[0].equals("13")) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				System.out.println("Successfully! " + date + " It's 13th on Friday");
			} else {
				System.out.println(date + " It's 13th, but it's not Friday");

			}
		} else {
			System.out.println(date + " It isn't 13th, and it isn't Friday");
		}
	}

	public static String passedTime(int day, int month, int year) {
		LocalDate localDate = LocalDate.now();
		LocalDate needdedDate = LocalDate.of(year, month, day);
		Period period = Period.between(needdedDate, localDate);
		String result = "Gone " + Integer.toString(period.getYears()) + " years, "
				+ Integer.toString(period.getMonths()) + " month, " + Integer.toString(period.getDays()) + " days.";
		System.out.println(result);
		return result;

	}

	public static void currentDateForLocaleCanadaGermanyPakistanVietnam() throws IOException {

		Locale pakistanLocale = new Locale("en", "PK");
		Locale vietnamLocale = new Locale("vi", "VN");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			System.out.print(
					"Please choose version of Java for working with dates. Please press button 7 or 8. To exit, press e.");
			String versionJava = reader.readLine();
			if ("7".equals(versionJava)) {

				// Java 7
				System.out.println("You choose Java 7 version.");
				Date date = new Date();
				DateFormat dateFormat = DateFormat.getDateInstance();

				dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL, Locale.CANADA);
				System.out.println("Local CANADA time is " + dateFormat.format(date));
				dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL, Locale.GERMAN);
				System.out.println("Local GERMANY time is " + dateFormat.format(date));
				dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL, pakistanLocale);
				System.out.println("Local PAKISTAN time is " + dateFormat.format(date));
				dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, vietnamLocale);
				System.out.println("Local VIETNAM time is " + dateFormat.format(date));
				System.out.println();
			} else if ("8".equals(versionJava)) {

				// Date-Time API Java 8
				System.out.println("You choose Java 8 version.");
				LocalDate currentLocalDate = LocalDate.now();

				DateTimeFormatter canadaFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
				DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
				DateTimeFormatter pakistanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
				DateTimeFormatter VietnamFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

				String localTimeGerman = currentLocalDate.format(canadaFormatter.withLocale(Locale.CANADA));
				String localTimeCanada = currentLocalDate.format(germanFormatter.withLocale(Locale.GERMAN));
				String localTimePakistan = currentLocalDate.format(pakistanFormatter.withLocale(pakistanLocale));
				String localTimeVietnam = currentLocalDate.format(VietnamFormatter.withLocale(vietnamLocale));
				System.out.println("Local Germany time is " + localTimeGerman);
				System.out.println("Local Canada time is " + localTimeCanada);
				System.out.println("Local Pakistan time is " + localTimePakistan);
				System.out.println("Local Vietnam time is " + localTimeVietnam);
			} else if (versionJava.toUpperCase().equals("E")) {
				System.out.println("");
				System.out.print("Thanks. Bye.");
				break;

			} else {
				System.out.println("");
				System.out.print("Please input valid value. ");
			}
		}

	}

	public static void main(String[] args) throws IOException, ParseException {
		// 1
		lengthEachMonthsAtDesiredYear(2015);

		// 2
		dateForAllMondays(2015, 11);

		// 3
		definitionFridayThirteenth("13-02-2015");

		// 4
		passedTime(10, 11, 2015);

		// 5
		currentDateForLocaleCanadaGermanyPakistanVietnam();
	}

}
