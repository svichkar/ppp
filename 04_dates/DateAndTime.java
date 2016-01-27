package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.format.*;

/**
 * Created by pantiukhin on 1/26/2016.
 * Subject: working with the Java DateTime API
 */
public class DateAndTime {
    public static void main(String[] args) {
        DateAndTime dAndTime = new DateAndTime();
        dAndTime.displayMonthsLength();
        dAndTime.displayMondays();
        dAndTime.fridayThirteenth();
        dAndTime.displayTimeElapsed();
        dAndTime.displayCurrentDateForVariousLocales();
    }

    //Displays the length of every month for the specified year
    public void displayMonthsLength() {
        Integer[] days = new Integer[12];
        String[] months = new String[12];
        int year = 0;
        Calendar calendar = new GregorianCalendar();
        calendar.setLenient(false);
        try {
            System.out.print("Enter the desired year and press Enter: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            year = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.YEAR, year);
        for (int i = 0; i < 12; i++) {
            calendar.set(Calendar.MONTH, i);
            months[i] = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            days[i] = calendar.getActualMaximum(Calendar.DATE);
        }
        System.out.println("The months for the year " + year + " have the following lengths: ");
        for (int i = 0; i < days.length; i++)
            System.out.println(months[i] + ": " + days[i]);
        System.out.println("==========================");
    }

    //Displays what dates for the specified year fall on Mondays
    public void displayMondays() {
        int year = 0;
        int month = 0;
        int numberOfDays = 0;
        int dayOfWeek = 0;
        ArrayList<Integer> listOfMondays = new ArrayList<Integer>();
        Calendar calendar = new GregorianCalendar();
        calendar.setLenient(false);
        try {
            System.out.println("Enter the desired year and press Enter: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            year = Integer.parseInt(reader.readLine());
            System.out.println("Enter the desired month number (0 for January) and press Enter: ");
            month = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        numberOfDays = calendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= numberOfDays; i++) {
            calendar.set(year, month, i);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == 2)
                listOfMondays.add(i);
        }
        String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        System.out.println("The following dates in the month of " + monthName + ", " + year + " fall on Monday: ");
        for (Integer day : listOfMondays) {
            System.out.println(day);
        }
        System.out.println("==========================");
    }

    //Determines whether the specified date falls on Friday, the 13th
    public void fridayThirteenth() {
        int year = 0;
        int month = 0;
        int dayOfWeek = 0;
        String monthName = "";
        String dayOfWeekName = "";
        try {
            System.out.println("Enter the desired year and press Enter: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            year = Integer.parseInt(reader.readLine());
            System.out.println("Enter the desired month number (0 for January) and press Enter: ");
            month = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setLenient(false);
        calendar.set(year, month, 13);
        monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeekName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        if (dayOfWeek == 6)
            System.out.println("The 13th of " + monthName + ", " + year + " is Friday");
        else {
            System.out.println("The 13th of " + monthName + ", " + year + " is not Friday. It is " + dayOfWeekName);
        }
        System.out.println("==========================");
    }

    //Displays a string that indicates how many years, months, and days have elapsed since the specified date
    public String displayTimeElapsed() {
        int year = 0;
        int month = 0;
        int day = 0;
        int difInYears = 0;
        int difInMonths = 0;
        int difInDays = 0;
        Calendar pastCalendar = Calendar.getInstance();
        pastCalendar.setLenient(false);
        try {
            System.out.println("Enter the desired year and press Enter: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            year = Integer.parseInt(reader.readLine());
            System.out.println("Enter the desired month number (0 for January) and press Enter: ");
            month = Integer.parseInt(reader.readLine());
            System.out.println("Enter the desired day (from 1 to 31) and press Enter: ");
            day = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pastCalendar.set(year, month, day);
        Calendar now = Calendar.getInstance();
        difInYears = now.get(Calendar.YEAR) - pastCalendar.get(Calendar.YEAR);
        if (pastCalendar.get(Calendar.MONTH) > now.get(Calendar.MONTH) ||
                (pastCalendar.get(Calendar.MONTH) == now.get(Calendar.MONTH) && pastCalendar.get(Calendar.DATE) > now.get(Calendar.DATE))) {
            difInYears--;
        }
        if ((pastCalendar.get(Calendar.MONTH) > now.get(Calendar.MONTH))) {
            difInMonths = 12 + (now.get(Calendar.MONTH) - pastCalendar.get(Calendar.MONTH));
        } else if ((pastCalendar.get(Calendar.MONTH) < now.get(Calendar.MONTH))) {
            difInMonths = now.get(Calendar.MONTH) - pastCalendar.get(Calendar.MONTH);
        }
        if ((pastCalendar.get(Calendar.DATE) > now.get(Calendar.DATE))) {
            difInMonths--;
            int num = pastCalendar.getActualMaximum(Calendar.DATE);
            difInDays = (num - pastCalendar.get(Calendar.DATE)) + now.get(Calendar.DATE);
        } else if ((pastCalendar.get(Calendar.DATE) < now.get(Calendar.DATE))) {
            difInDays = now.get(Calendar.DATE) - pastCalendar.get(Calendar.DATE);
        }
        String result = "Number of years passed: " + difInYears + "\r\n" +
                "Number of months passed: " + difInMonths + "\r\n" +
                "Number of days passed: " + difInDays;
        System.out.println(result);
        System.out.println("==========================");
        return result;
    }

    /*
    Displays the current date in the full format using the locales of Canada, Germany, Pakistan, and Vietnam
    APIs: Java7 and Java8
    */
    public void displayCurrentDateForVariousLocales() {
        String pattern = "EEE, d MMM yyyy HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String[] zones = {"Canada/Pacific", "Europe/Berlin", "Asia/Karachi", "Asia/Ho_Chi_Minh"};
        TimeZone[] timeZones = new TimeZone[4];
        System.out.println("==========================");
        System.out.println("Java 7: ");

        for (int i = 0; i < zones.length; i++) {
            Calendar cal = Calendar.getInstance();
            timeZones[i] = TimeZone.getTimeZone(zones[i]);
            cal.setTimeZone(timeZones[i]);
            format.setTimeZone(timeZones[i]);
            System.out.println(timeZones[i].getDisplayName() + ":");
            System.out.println(format.format(cal.getTime()));
        }
        System.out.println("==========================");
        System.out.println("Java 8:");
        DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern(pattern);
        for (int i = 0; i < zones.length; i++) {
            ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(zones[i]));
            System.out.println(ZoneId.of(zones[i]) + ":");
            System.out.println(zdt.format(dtformatter));
        }
    }
}
