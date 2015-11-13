package com.nixsolutions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TimePeriodDetermining {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Please enter a beginning date in MM-DD-YYYY format");
		Scanner in = new Scanner(System.in);
		String inputDate = in.nextLine();
		in.close();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		try {
			Date date = dateFormat.parse(inputDate);
			new TimePeriodDetermining().timePeriodCounter(date);
		} catch (ParseException ex) {
			System.out.println("You put data in ivalid format.");
		}
	}

	private void timePeriodCounter(Date beginningDate) {
		Date currentDate = new Date();
		Calendar difference = Calendar.getInstance();
		difference.setTime(new Date(currentDate.getTime() - beginningDate.getTime()));
		System.out.printf("From your date passed %d year(s), %d month(s), %d day(s)%n", (difference.get(Calendar.YEAR) - 1970),
				difference.get(Calendar.MONTH), difference.get(Calendar.DAY_OF_MONTH)-1);
	}
}
