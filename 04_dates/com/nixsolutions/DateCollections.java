package com.nixsolutions;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateCollections {

    public static String[] months = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
            "OCTOBER", "NOVEMBER", "DECEMBER"};

    public static void daysInEachMonth(int year) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.DAY_OF_MONTH, 1); //added row
        for (int i = 0; i < 12; i++) {
            c.set(Calendar.MONTH, i);
            System.out.println(months[i] + ": " +c.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
    }

    public static void mondaysInMonth(int year, int month) {
        if (month < 0 || month > 11) {
            System.out.println("Incorrect month. Please set 0-11 format.");
            return;
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);

        int firstMonday = c.get(Calendar.DAY_OF_MONTH);
        int dayInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        while (firstMonday <= dayInMonth) {
            System.out.print(firstMonday + " ");
            firstMonday +=7;
        }
    }

    public static boolean isFriday13(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if ("Friday".equals(c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US))
                && c.get(Calendar.DAY_OF_MONTH) == 13) {
            return true;
        }
        return false;
    }

    public static String timeGoneJava7(Date date) {
        long secDiff = new Date().getTime() - date.getTime();
        if (secDiff >= 0) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(secDiff);
            return (c.get(Calendar.YEAR) - 1970) + " year, " + (c.get(Calendar.MONTH)) + " month, "
                    + (c.get(Calendar.DAY_OF_MONTH )) + " day";
        } else {
            return "Input date bigger then current date";
        }
    }

    public static String timeGoneJava8(Date date) {
        long secDiff = new Date().getTime() - date.getTime();
        if (secDiff >= 0) {
            LocalDate today = LocalDate.now();
            LocalDate yesterday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Period oneDay = Period.between(today, yesterday);
            return oneDay.toString();
        } else {
            return "Input date bigger then current date";
        }
    }

    public static void printCurrentDateForSomeLocal() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println("Canada: " + localDate.format(dtf.withLocale(Locale.CANADA)));
        System.out.println("Germany: " + localDate.format(dtf.withLocale(Locale.GERMANY)));
        System.out.println("Pakistan(used English language): " + localDate.format(dtf.withLocale(
                new Locale("en", "PK"))));
        System.out.println("Vietnam: " + localDate.format(dtf.withLocale(new Locale("vi", "VN"))));
    }

}
