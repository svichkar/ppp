package com.manetskiy;


import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Dates {

    private static Calendar calendar;

    public static void main(String[] args) {
        daysInMonths(2016);
        mondays(1, 2016);
        System.out.println(isFridayThirteen(new Date()));
        timePassed(new Date());
        locales();
    }

    private static void daysInMonths(int year){
        calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        String[] months = DateFormatSymbols.getInstance().getMonths();
        for (int i = 0; i < months.length-1; i++){
            calendar.set(Calendar.MONTH, i);
            String s = months[i];
            System.out.println(s + ": " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
    }

    private static void mondays(int month, int year){
        calendar = new GregorianCalendar();
        calendar.setLenient(false);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        for (int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            calendar.set(Calendar.DAY_OF_MONTH, i);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
                System.out.println(i);
        }
    }

    private static boolean isFridayThirteen(Date date){
        calendar = new GregorianCalendar();
        calendar.setTime(date);
        return (calendar.get(Calendar.DAY_OF_MONTH)==13)&&(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY);
    }

    private static void timePassed(Date date){

        if (date.getTime() <= new Date().getTime()){
            long diff = new Date().getTime() - date.getTime();
            Calendar inputDate  = new GregorianCalendar();
            inputDate.setTimeInMillis(diff);
            System.out.println((inputDate.get(Calendar.YEAR)-1970) + " year(s) " + inputDate.get(Calendar.MONTH) + " month(s) " +
                    (inputDate.get(Calendar.DAY_OF_MONTH)-1) + " day(s) passed.");
        }
        else {
            System.out.println("Cannot accept date that has not yet begun.");
        }
    }

    private static void locales(){
        List<Locale> locales = new ArrayList<Locale>(){
            {add(new Locale("en","CA"));}
            {add(new Locale("de", "DE"));}
            {add(new Locale("hi", "IN"));}
            {add(new Locale("vi", "VN"));}
        };
        locales.forEach(locale -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
            LocalDate localDate = LocalDate.now();
            System.out.println(localDate.format(dtf.withLocale(locale)));
        });
    }




}
