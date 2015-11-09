package com.nixsolutions;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Rybkinrolla on 09.11.2015.
 */
public class WorkWithDate {
    private static void quantityOfDaysByYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, year);
        for (int i = 0; i < 12; i++) {
            cal.set(cal.MONTH, i);
            System.out.println(cal.getDisplayName(cal.MONTH, cal.LONG, Locale.US) + " - " + cal.getActualMaximum(cal.DAY_OF_MONTH));
        }

    }

    private static void dateOnMonday(int year, String month) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, year);
        cal.setTime(new SimpleDateFormat("MMM", Locale.US).parse(month));
        for (int i = 1; i <= cal.getActualMaximum(cal.DAY_OF_MONTH); i++) {
            cal.set(cal.DAY_OF_MONTH, i);
            if (cal.getDisplayName(cal.DAY_OF_WEEK, cal.LONG, Locale.US).equals("Monday")) {
                System.out.println(cal.get(cal.DAY_OF_MONTH));
            }

        }

    }

    private static void isThirteenthFriday(int year, String month, int day) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, year);
        cal.setTime(new SimpleDateFormat("MMM", Locale.US).parse(month));
        cal.set(cal.DAY_OF_MONTH, day);
        if (cal.getDisplayName(cal.DAY_OF_WEEK, cal.LONG, Locale.US).equals("Friday") && cal.get(cal.DAY_OF_MONTH) == 13) {
            System.out.println("Yes, this is the Friday 13th");
        } else {
            System.out.println("No, this is not the Friday 13th");
        }

    }

    private static void dateDifference(int year, String month, int day) throws ParseException {
        Calendar passedDate = Calendar.getInstance();
        passedDate.setTime(new SimpleDateFormat("MMM", Locale.US).parse(month));
        passedDate.set(passedDate.YEAR, year);
        passedDate.set(passedDate.DAY_OF_MONTH, day);
        long millisDifference = System.currentTimeMillis() - passedDate.getTimeInMillis();
        passedDate.setTimeInMillis(millisDifference);
        System.out.println((passedDate.get(passedDate.YEAR) - 1970) + " year(s)" + passedDate.get(passedDate.MONTH) + " month(s)" + (passedDate.get(passedDate.DAY_OF_MONTH) - 1) + " day(s) passed");
    }


    public static void main(String[] args) {
        WorkWithDate.quantityOfDaysByYear(2016);
        try {
            WorkWithDate.dateOnMonday(2015, "November");
        } catch (ParseException e){
            System.out.println("Try to type month properly in English");
        }
        try {
            WorkWithDate.isThirteenthFriday(2015, "November",13);
        } catch (ParseException e){
            System.out.println("Try to type month properly in English");
        }
        try {
            WorkWithDate.dateDifference(2066, "September", 9);
        } catch (ParseException e) {
            System.out.println("Try to type month properly in English");
        }
        Date date = new Date();
        LocalDate date2 = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println("Java 7: " + DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA).format(date) + " - Java 8: " +
                date2.format(dtf.withLocale(Locale.CANADA)));
        System.out.println("Java 7: " + DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY).format(date) + " - Java 8: " +
                date2.format(dtf.withLocale(Locale.GERMANY)));
        Locale pak = new Locale("pa","PK");
        System.out.println("Java 7: " + DateFormat.getDateInstance(DateFormat.FULL, pak).format(date) + " - Java 8: " +
                date2.format(dtf.withLocale(pak)));
        Locale vie = new Locale("vi","VN");
        System.out.println("Java 7: " + DateFormat.getDateInstance(DateFormat.FULL, vie).format(date) + " - Java 8: " +
                date2.format(dtf.withLocale(vie)));

    }
}
