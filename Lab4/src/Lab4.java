import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Lab4 {

	public static void main(String[] args) throws IOException, ParseException {
		// 1
		System.out.println("***First task***");
		System.out.println("Enter year: ");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(in);
		String year = keyboard.readLine();
		lengthOfMonth(year);

		// 2
		System.out.println("***Second task***");
		System.out.println("Enter month and year in next format mm-yyyy (e.g. 02-2015):");
		String date = keyboard.readLine();
		mondaysInMonth(date);

		// 3
		System.out.println("***Third task***");
		System.out.println("Enter month and year in next format dd-MM-yyyy (e.g. 13-03-2015):");
		date = keyboard.readLine();
		isDateFridayThirteenth(date);

		// 4
		System.out.println("***Fourth task***");
		System.out.println("Enter month and year in next format dd-MM-yyyy (e.g. 13-03-2015):");
		date = keyboard.readLine();
		System.out.println(howMuchTimePassed(date));

		//5 
		System.out.println("***Fifth task - Java 7***");
		Date dateToPrint = new Date();
		DateFormat simpleDateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL, Locale.CANADA);
		System.out.println(String.format("Date Canada: %s", simpleDateFormat.format(dateToPrint)));
		simpleDateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL, Locale.GERMAN);
		System.out.println(String.format("Date Germany: %s", simpleDateFormat.format(dateToPrint)));
		Locale localePakistan = new Locale("en", "PK");
		simpleDateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL, localePakistan);
		System.out.println(String.format("Date Pakistan: %s", simpleDateFormat.format(dateToPrint)));
		Locale localeVietnam = new Locale("vi", "VN");
		simpleDateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL, localeVietnam);
		System.out.println(String.format("Date Vietnam: %s", simpleDateFormat.format(dateToPrint)));

		System.out.println("***Fifth task - Java 8***");
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		System.out.println(String.format("Date Canada: %s", localDate.format(dateTimeFormatter.withLocale(Locale.CANADA))));
		System.out.println(String.format("Date Germany: %s", localDate.format(dateTimeFormatter.withLocale(Locale.GERMAN))));
		System.out.println(String.format("Date Pakistan: %s", localDate.format(dateTimeFormatter.withLocale(localePakistan))));
		System.out.println(String.format("Date Vietnam: %s", localDate.format(dateTimeFormatter.withLocale(localeVietnam))));
	}

	public static void lengthOfMonth(String year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.valueOf(year));
		for (int i = 0; i < 12; i++) {
			calendar.set(Calendar.MONTH, i);
			int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
			System.out.println(String.format("The number of days in the month of %s: %s", monthName, numberOfDays));
		}
	}

	public static void mondaysInMonth(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.valueOf(date.split("-")[1]));
		calendar.set(Calendar.MONTH, Integer.valueOf(date.split("-")[0]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int inputMonth = calendar.get(Calendar.MONTH);
		for (int i = 1; calendar.get(Calendar.MONTH) == inputMonth
				&& i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				System.out.println(String.format("Monday: %s", calendar.get(Calendar.DATE)));
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
	}

	public static void isDateFridayThirteenth(String inputDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		Date date = sdf.parse(inputDate);
		calendar.setTime(date);
		System.out.println(calendar.getTime());
		if (inputDate.split("-")[0].equals("13")) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				System.out.println(String.format("Date '%s' is friday the thirteenth!", inputDate));
				return;
			}
		}
		System.out.println(String.format("Date '%s' is not friday. It is %s", inputDate,
				calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)));
	}

	public static String howMuchTimePassed(String inputDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		Date date = sdf.parse(inputDate);
		calendar.setTime(date);
		long dif = Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis();
		long daysTotal = dif / (24 * 60 * 60 * 1000);
		long years = daysTotal / 365;
		long restOfDaysAfterYear = daysTotal % 365;
		long months = restOfDaysAfterYear * 12 / 365;
		long restOfDaysAfterMonth = 365 * months / 12;
		long days = restOfDaysAfterYear - restOfDaysAfterMonth;
		StringBuilder sb = new StringBuilder();
		sb.append("Elapsed: ").append(years).append(" years, ");
		sb.append(months).append(" months and ").append(days).append(" days");
		return sb.toString();
	}
}
