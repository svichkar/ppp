package com.nixsolutions;


import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

/**
 * Created by Sergey on 07.11.2015.
 */
public class Dates {

    static void LengthOfMonth(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        for (int i = 0; i < 12; i++) {
            calendar.set(Calendar.MONTH, i);
            System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
    }

    static void DatesForMondays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        for (int i = 0; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
            }
        }
    }

    static boolean Friday13th(int year, int month, int day) {
        boolean state;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        state = (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY & day == 13);
        return state;
    }

    static String HowLongAgo(int year, int month, int day) {
        String temp="";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        Calendar currentcalendar = Calendar.getInstance();
        if (calendar.before(currentcalendar)) {
            int deltaYear = currentcalendar.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
            int deltaMonth = currentcalendar.get(Calendar.MONTH) - calendar.get(Calendar.MONTH);
            int deltaDay = currentcalendar.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
            if (deltaDay < 0) {
                deltaDay += calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                deltaMonth -= 1;
            }
            if (deltaMonth < 0) {
                deltaMonth += 12;
                deltaYear -= 1;
            }
            temp = deltaYear + " years, " + deltaMonth + " month, " + deltaDay + " days";
        }
        else {
            temp="Your date isn't in pass";
        }
        return temp;
    }

    public static void main(String[] args) throws InputMismatchException, ParseException {

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the year:");
        int year = in.nextInt();
        System.out.println("Please enter the month:");
        int month = in.nextInt();
        System.out.println("Please enter the day:");
        int day = in.nextInt();

        Dates.LengthOfMonth(year);
        Dates.DatesForMondays(year, month);
        System.out.println(Dates.Friday13th(year, month, day));
        System.out.println(Dates.HowLongAgo(year, month, day));

        LocalDate current = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);

        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA);
        System.out.println("Canada\t" + current.format(dtf.withLocale(Locale.CANADA)) + "\t" + df.format(new Date()));

        df = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
        System.out.println("Germany\t" + current.format(dtf.withLocale(Locale.GERMANY)) + "\t" + df.format(new Date()));

        Locale pakistan = new Locale("pa", "PK");
        df = DateFormat.getDateInstance(DateFormat.FULL, pakistan);
        System.out.println("Pakistan\t" + current.format(dtf.withLocale(pakistan)) + "\t" + df.format(new Date()));

        Locale vietnam = new Locale("vi", "VN");
        df = DateFormat.getDateInstance(DateFormat.FULL, vietnam);
        System.out.println("Vietnam\t" + current.format(dtf.withLocale(vietnam)) + "\t" + df.format(new Date()));
    }
}
