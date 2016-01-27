//Dates.java
package com.nixsolutions;
import java.text.*;
import java.util.*;
import java.lang.Math;

public class Dates {
	public static void main(String[] args) {
		monthLength();
		getDatesOfDays();
		ifFridayTheThirteenth();
/* Doesn't work at the moment
		ymdSinceDate(); */
	}
	static void monthLength() {
		Scanner scan = new Scanner(System.in);
		String[] months = new DateFormatSymbols().getMonths();
		System.out.print("1. Enter the year to show the length of each month in it: ");
		int year = scan.nextInt();
		for (int i = 0; i < months.length - 1; i++) {
		String month = months[i];
		Calendar calendar = new GregorianCalendar(year, i + 1, 1);
		int monthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(month + " has " + monthLength + " days");
		}
	}
	static void getDatesOfDays() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("2. Enter the year and month in yyyy/MM format to show all Mondays from:");
			String ym = scan.next();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(dateFormat.parse(ym));
			int month = calendar.get(Calendar.MONTH);
			System.out.println("The following days of the given month are Mondays:");
			while(calendar.get(Calendar.MONTH) == month) {
				int day = calendar.get(Calendar.DAY_OF_WEEK);
				if (day == Calendar.MONDAY) {
					System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
				}
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	static void ifFridayTheThirteenth() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("3. Enter the date in yyyy/MM/dd format to check if it's a Friday the 13th:");
			String ymd = scan.next();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(dateFormat.parse(ymd));
			int dow = calendar.get(Calendar.DAY_OF_WEEK);
			int dom = calendar.get(Calendar.DAY_OF_MONTH);
			if (dow == Calendar.FRIDAY && dom == 13) {
				System.out.println("\nThis day is a Friday the 13th.");
			} else {
				System.out.println("\nNot a Friday the 13th.");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	static void ymdSinceDate() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("4. Enter the date in yyyy/MM/dd format to check how many years, months and days passed since then:");
			String ymd = scan.next();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
			Calendar oldDate=Calendar.getInstance();
			oldDate.setTime(dateFormat.parse(ymd));
			Calendar curDate=Calendar.getInstance();
			long diff = (curDate.getTime()) - (oldDate.getTime());
			Date results = new Date(diff);
			Format frmt = new SimpleDateFormat("yy MM dd");
			System.out.println(frmt.format(results).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}