/**
 * 
 */
package com.nixsolutions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * @author mixeyes
 *
 */
public class TimeInterval {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		System.out.println("Please enter some date in format:dd.MM.yyyy \n");
		Scanner in = new Scanner(System.in);
		String date = in.nextLine();
		in.close();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

		Date dd = df.parse(date + " 00:00:00");
		Date today = new Date();
		today.setHours(00);
		today.setMinutes(00);
		today.setSeconds(00);

		Date diffDate = new Date(today.getTime() - dd.getTime());
		long dif = diffDate.getTime();
		long daysCount;
		long monthCount;
		long yearCount;
		if (dif >= 86400000L) {
			daysCount = dif / 86400000L;
			if (daysCount >= 365) {
				yearCount = daysCount / 365;
				daysCount = daysCount % 365;
				if (daysCount >= 31) {
					monthCount = daysCount / 31;
					daysCount = daysCount % 31;
					System.out.println(yearCount + " years " + monthCount + " month(es) " + daysCount + " day(s)\n");
				} else {
					System.out.println(yearCount + " year(s)\n");
				}
			} else {
				if (daysCount >= 31) {
					monthCount = daysCount / 31;
					daysCount = daysCount % 31;
					System.out.println(monthCount + " month(es) " + daysCount + " day(s)\n");
				} else {
					System.out.println(daysCount + " day(s)\n");
				}
			}
		} else {
			System.out.println("Interval is less then 1 day\n");
		}

	}

}
