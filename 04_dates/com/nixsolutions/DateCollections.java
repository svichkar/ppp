package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.*;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateCollections {

    public static void daysInEachMonth(int year) {
        String[] months = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
                "OCTOBER", "NOVEMBER", "DECEMBER"};
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        for (int i = 0; i < 12; i++) {
            c.set(Calendar.MONTH, i);
            System.out.println(months[i] + ": " +c.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
    }

    public static void mondaysInMonth(int year, int month) {
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

    public static String timeGone(Date date) {
        long secDiff = new Date().getTime() - date.getTime();
        if (secDiff >= 0) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(secDiff);
            return (c.get(Calendar.YEAR) - 1970) + " year, " + (c.get(Calendar.MONTH)) + " month, "
                    + (c.get(Calendar.DAY_OF_MONTH) - 1) + " day";
        } else {
            return "Input date bigger then current date";
        }
    }

    public static void printCurrentDateForSomeLocal() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println(localDate.format(dtf.withLocale(Locale.GERMAN)));
        System.out.println(localDate.format(dtf.withLocale(Locale.CANADA)));
    }
}
