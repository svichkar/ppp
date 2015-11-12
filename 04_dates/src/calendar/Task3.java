package calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Task3 {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LengthOfMonth();
		CheckMonday();
		CheckFridayThirteen();
		CheckMonday();

	}

	//// Написать метод который для указанного года выводит длину каждого месяца
	public static void LengthOfMonth() {
		int year;

		System.out.print("метод который для указанного года выводит длину каждого месяца");

		System.out.print("Provide year: ");
		year = Integer.parseInt(scan.nextLine());

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);

		for (int i = 0; i < 12; i++) {

			calendar.set(Calendar.MONTH, i);

			System.out.println(Month.of(i + 1));
			System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		}

	}

	// Написать метод который для указанного месяца и года выводит список дат
	// которые выпадают на понедельник.

	public static void CheckMonday() {

		int year;
		int month;
		int countDays;
		int dayOfWeek;

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("dd/MM/yyyy");

		System.out.println(
				"метод который для указанного месяца и года выводит список дат которые выпадают на понедельник");
		System.out.print("Provide year: ");
		year = Integer.parseInt(scan.nextLine());

		System.out.print("Provide month: ");
		month = Integer.parseInt(scan.nextLine());

		Calendar calendar = GregorianCalendar.getInstance();

		calendar.clear();
		calendar.set(year, month, 1);

		countDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 1; i < countDays + 1; i++) {

			dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

			if (dayOfWeek == 1) {

				System.out.println(df.format(calendar.getTime()));
			}

			calendar.add(Calendar.DATE, 1);

		}
	}

	// Написать метод который проверяет является ли указанная дата пятницей
	// тринадцатого.
	public static void CheckFridayThirteen() {

		System.out.println("Написать метод который проверяет является ли указанная дата пятницей тринадцатого");

		int year;
		int month;
		int day;

		Calendar calendar = GregorianCalendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("dd/MM/yyyy");

		System.out.print("Provide year: ");
		year = Integer.parseInt(scan.nextLine());

		System.out.print("Provide month: ");
		month = Integer.parseInt(scan.nextLine());

		System.out.print("Provide day: ");
		day = Integer.parseInt(scan.nextLine());

		calendar.clear();

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);

		if (((calendar.get(Calendar.DAY_OF_WEEK)) == 6) && ((calendar.get(Calendar.DAY_OF_MONTH)) == 13)) { // 6=FRIDAY

			System.out.println("Friday 13");
			System.out.println(df.format(calendar.getTime()));

		}

		else {

			System.out.println("!=Friday 13");
		}

	}

	// Написать метод который для указанной даты возвращает строку в которой
	// написано сколько лет, месяцев, дней прошло с этой даты. Например “2 года,
	// 1 месяц и 23 дня
	public static void DifferenceInDates() {

		System.out.println(
				"Написать метод который для указанной даты возвращает строку в которой написано сколько лет, месяцев, дней прошло с этой даты.");

		int year;
		int month;
		int day;
		long Diff;
		long CurrentMillis;
		long alldays;
		int Years = 0;
		int Months = 0;
		int Days = 0;
		double DaysInYear = 365.25;
		int DaysInMonth = 30;
		int leftDays = 0;

		Calendar calendar = GregorianCalendar.getInstance();

		CurrentMillis = calendar.getTimeInMillis();

		System.out.print("Provide year: ");
		year = Integer.parseInt(scan.nextLine());

		System.out.print("Provide month: ");
		month = Integer.parseInt(scan.nextLine());

		System.out.print("Provide day: ");
		day = Integer.parseInt(scan.nextLine());

		calendar.clear();

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);

		Diff = CurrentMillis - calendar.getTimeInMillis();

		alldays = TimeUnit.MILLISECONDS.toDays(Diff);

		leftDays = (int) (alldays % DaysInYear);
		Years = (int) ((alldays - leftDays) / DaysInYear);
		Days = leftDays % DaysInMonth;
		Months = (int) (leftDays - Days) / DaysInMonth;

		System.out.println("Years=" + Years);
		System.out.println("Months=" + Months);
		System.out.println("Days=" + Days);

	}

	// Используя локаль для Канады, Германии, Пакистана и Вьетнама вывести
	// текущую дату в полном формате используя Date-Time API Java 8 и
	// возможности Java 7
	public static void AllLocalDates() {

		System.out.println(
				"Используя локаль для Канады, Германии, Пакистана и Вьетнама вывести текущую дату в полном формате используя Date-Time API Java 8 и возможности Java 7");

		LocalDate curDate = LocalDate.now();
		DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);

		System.out.println("Canada" + curDate.format(df.withLocale(Locale.CANADA)));
		System.out.println("Germany" + curDate.format(df.withLocale(Locale.GERMANY)));
		Locale pakistan = new Locale("ur", "PK");
		System.out.println("Pakistan date: " + curDate.format(df.withLocale(pakistan)));
		Locale vietnam = new Locale("vi", "VN");
		System.out.println("Vietnam date: " + curDate.format(df.withLocale(vietnam)));

		Date currentDate = new Date();
		DateFormat dateFormat;
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA);
		System.out.println("Canada date: " + dateFormat.format(currentDate));
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
		System.out.println("Germany date: " + dateFormat.format(currentDate));
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL, pakistan);
		System.out.println("Pakistan date: " + dateFormat.format(currentDate));
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL, vietnam);
		System.out.println("Vietnam date: " + dateFormat.format(currentDate));

	}

}
