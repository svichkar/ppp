// Dates.java
package com.nixsolutions;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class Dates {
  private static void monthLength() {
    Scanner scan = new Scanner(System.in);
    String[] months = new DateFormatSymbols().getMonths();
    
    System.out.println("\n1. Enter the year to show the length of each month in it: ");
    int year = scan.nextInt();
    
    for (int i = 0; i < months.length - 1; i++) {
      String month = months[i];
      Calendar calendar = new GregorianCalendar(year, i, 1);
      int monthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
      System.out.println(month + " has " + monthLength + " days");
    }
  }
  
  private static void getDatesOfDays() {
    try {
      Scanner scan = new Scanner(System.in);
      System.out.println("\n2. Enter the year and month in yyyy/MM format"
                          + "to show all Mondays from:");
      String ym = scan.next();
      
      SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM");
      Calendar calendar=Calendar.getInstance();
      calendar.setLenient(false);
      calendar.setTime(dateFormat.parse(ym));
      int month = calendar.get(Calendar.MONTH);
      
      System.out.println("The following days of the given month are Mondays:");
      
      while (calendar.get(Calendar.MONTH) == month) {
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
  
  private static void ifFridayTheThirteenth() {
    try {
      Scanner scan = new Scanner(System.in);
      System.out.println("\n3. Enter the date in yyyy/MM/dd format to check"
                          + " if it's a Friday the 13th:");
      String ymd = scan.next();
      
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
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
  
  private static void ymdSinceDate() {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n4. Enter the date in yyyy-MM-dd format to check"
                        + " how many years, months and days passed since then:");
    String ymd = scan.next();
    
    LocalDate oldDate = LocalDate.parse(ymd); // Using ISO date formatter
    LocalDate curDate = LocalDate.now();
    Period p = Period.between(oldDate,curDate);
    
    System.out.println(p.getYears() + " years, "
                        + p.getMonths() + " months, "
                        + p.getDays() + " days");
  }
  
  private static void fullDateRegional() {
    DateFormat dateFormatCanada, dateFormatGermany;
    String dateFormatPakistan, dateFormatVietnam;
    
    System.out.println("\n5. Printing current date in full format:");
    System.out.println("\n. ... for Canada and Germany using features available in Java 7:");
    dateFormatCanada = DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA);
    dateFormatGermany = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMAN);
    Calendar calendar = Calendar.getInstance();
    
    System.out.println(dateFormatCanada.format(calendar.getTime()));
    System.out.println(dateFormatGermany.format(calendar.getTime()));
    
    System.out.println("\n. ... for Pakistan and Vietnam using features available in Java 8:");
    dateFormatPakistan = 
      DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
        .withLocale(new Locale("en", "PK"))
        .format(LocalDate.now());
    dateFormatVietnam = 
      DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
        .withLocale(new Locale("vi", "VN"))
        .format(LocalDate.now());
        
    System.out.println(dateFormatPakistan);
    System.out.println(dateFormatVietnam);
  }
  
  public static void main(String[] args) {
    monthLength();
    getDatesOfDays();
    ifFridayTheThirteenth();
    ymdSinceDate();
    fullDateRegional();
  }
  
}