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
	static Calendar calendar = GregorianCalendar.getInstance();

	public static void main(String[] args) {

		//LengthOfMonth();
		// CheckMonday();
		 //CheckFridayThirteen();
		DifferenceInDates();
		// AllLocalDates();

	}

	public static int getYearFromKeyBoard() {

		int year = 0;
		System.out.println("Provide year: ");
		try {
			year = Integer.parseInt(scan.nextLine());

		} catch (NumberFormatException e) {

			System.out.println("It is incorrect year. Year =1999");
			year = 1999;
		}
		return year;

	}

	public static int getMonthFromKeyBoard() {

		int month = 0;
		System.out.print("Provide month: ");
		try {
			month = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {

			System.out.println("It is incorrect month. Month = 4");
			month = 4;
		}
		return month;

	}

	public static int getDayFromKeyBoard() {

		int day = 0;
		System.out.print("Provide day: ");
		try {
			day = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {

			System.out.println("It is incorrect Day. Day = 15");
			day = 15;
		}
		return day;

	}

	//// �������� ����� ������� ��� ���������� ���� ������� ����� ������� ������
	public static void LengthOfMonth() {

		System.out.println("����� ������� ��� ���������� ���� ������� ����� ������� ������");

		calendar.set(Calendar.YEAR, getYearFromKeyBoard());

		for (int i = 0; i < 12; i++) {

			calendar.set(Calendar.MONTH, i);

			System.out.println(Month.of(i + 1));
			System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		}

	}

	// �������� ����� ������� ��� ���������� ������ � ���� ������� ������ ���
	// ������� �������� �� �����������.

	public static void CheckMonday() {

		System.out.println(
				"�������� ����� ������� ��� ���������� ������ � ���� ������� ������ ��� ������� �������� �� �����������.");

		int countDays;
		int dayOfWeek;

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
								
		calendar.clear();
		calendar.set(getYearFromKeyBoard(), getMonthFromKeyBoard()-1, 1);

		countDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 1; i < countDays + 1; i++) {

			dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

			if (dayOfWeek == 1) {

				System.out.println(df.format(calendar.getTime()));
			}
			calendar.add(Calendar.DATE, 1);
		}
	}

	// �������� ����� ������� ��������� �������� �� ��������� ���� ��������
	// ������������.
	public static void CheckFridayThirteen() {

		System.out.println("�������� ����� ������� ��������� �������� �� ��������� ���� �������� ������������");

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				
		calendar.clear();
		calendar.set(getYearFromKeyBoard(), getMonthFromKeyBoard()-1, getDayFromKeyBoard());
		
		if (((calendar.get(Calendar.DAY_OF_WEEK)) == 6) && ((calendar.get(Calendar.DAY_OF_MONTH)) == 13)) { // 6=FRIDAY

			System.out.println("Friday 13");
			System.out.println(df.format(calendar.getTime()));
		}
		else {

			System.out.println("!=Friday 13");
		}

	}

	// �������� ����� ������� ��� ��������� ���� ���������� ������ � �������
	// �������� ������� ���, �������, ���� ������ � ���� ����. �������� �2 ����,
	// 1 ����� � 23 ���
	public static void DifferenceInDates() {

		System.out.println(
				"�������� ����� ������� ��� ��������� ���� ���������� ������ � ������� �������� ������� ���, �������, ���� ������ � ���� ����.");

		long diff;
		long currentMillis;
		long alldays;
		int Years = 0;
		int Months = 0;
		int Days = 0;
		double DaysInYear = 365.25;
		int DaysInMonth = 30;
		int leftDays = 0;

		currentMillis = calendar.getTimeInMillis();
								
		calendar.clear();
		calendar.set(getYearFromKeyBoard(), getMonthFromKeyBoard()-1, getDayFromKeyBoard());

		diff = currentMillis - calendar.getTimeInMillis();

		alldays = TimeUnit.MILLISECONDS.toDays(diff);

		leftDays = (int) (alldays % DaysInYear);
		Years = (int) ((alldays - leftDays) / DaysInYear);
		Days = leftDays % DaysInMonth;
		Months = (int) (leftDays - Days) / DaysInMonth;

		System.out.println("Years=" + Years);
		System.out.println("Months=" + Months);
		System.out.println("Days=" + Days);

	}

	// ��������� ������ ��� ������, ��������, ��������� � �������� �������
	// ������� ���� � ������ ������� ��������� Date-Time API Java 8 �
	// ����������� Java 7
	public static void AllLocalDates() {

		System.out.println(
				"��������� ������ ��� ������, ��������, ��������� � �������� ������� ������� ���� � ������ ������� ��������� Date-Time API Java 8 � ����������� Java 7");

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
