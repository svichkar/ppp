/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datesworkshop;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Mednor
 */
public class DatesWorkshop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new DatesWorkshop().getMonthsLength(2001);
        new DatesWorkshop().getMondays(2015, 9);
        System.out.printf("\nnew DatesWorkshop().isFriday13th returns: %s\n",
                new DatesWorkshop().isFriday13th(2015, 10, 13));
        System.out.print(new DatesWorkshop().elapsedTime(2015, 9, 9));
        new DatesWorkshop().getCurrentDateJVM7();
        new DatesWorkshop().getCurrentDateJVM8();
    }

    /**
     * Task1: Написать метод который для указанного года выводит длину каждого месяца.
     *
     * @param year Year, AD
     */
    public void getMonthsLength(int year) {

        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.YEAR, year);
        System.out.printf("\nThe year %d days/month stats:\n", year);
        for (int i = 0; i < 12; i++) {
            currentDate.set(Calendar.MONTH, i);
            System.out.printf("%s: %d days\n",
                    currentDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH),
                    currentDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
    }

    /**
     * Task2: Написать метод который для указанного месяца и года выводит список дат которые
     * выпадают на понедельник.
     *
     * @param year Year, AD
     * @param month Month number 0 to 11
     */
    public void getMondays(int year, int month) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.YEAR, year);
        currentDate.set(Calendar.MONTH, month);
        int mondays = 0;
        for (int day = 1; day <= currentDate.getActualMaximum(Calendar.DAY_OF_MONTH); day++) {
            currentDate.set(Calendar.DAY_OF_MONTH, day);
            if (currentDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                mondays++;
            }
        }

        System.out.printf("\n%s %d has %d Mondays\n",
                currentDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH), year,
                mondays);
    }

    /**
     * Task3: Написать метод который проверяет является ли указанная дата пятницей тринадцатого.
     *
     * @param year Year, AD
     * @param month Month number 0 to 11
     * @param day Day number 1 to 31
     */
    public boolean isFriday13th(int year, int month, int day) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setLenient(false);
        currentDate.set(year, month, day);
        currentDate.getTime();
        if (currentDate.get(Calendar.DAY_OF_MONTH) == 13
                && currentDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Task4: Написать метод который для указанной даты возвращает строку в которой написано сколько
     * лет, месяцев, дней прошло с этой даты. Например “2 года, 1 месяц и 23 дня”
     *
     * @param year Year, AD. Date must be current date or the past one.
     * @param month Month number 0 to 11. Date must be current date or the past one.
     * @param day Day number 1 to 31. Date must be current date or the past one.
     *
     */
    public String elapsedTime(int year, int month, int day) {
        Calendar firstDate = Calendar.getInstance();
        firstDate.setLenient(false);
        firstDate.set(year, month, day);
        Calendar lastDate = Calendar.getInstance();
        //lastDate.set(2015, 7, 29);//set last date here for debugging options
        if (lastDate.compareTo(firstDate) >= 0) {
            lastDate.setTimeInMillis(lastDate.getTimeInMillis() - firstDate.getTimeInMillis());
            return "\nElapsed time: " + new Integer(lastDate.get(Calendar.YEAR) - 1970).toString()
                    + " years, " + new Integer(lastDate.get(Calendar.MONTH)).toString()
                    + " months, " + new Integer(lastDate.get(Calendar.DAY_OF_MONTH) - 1).toString()
                    + " days.\n";
        } else {
            return "\nThe date you have entered do not exist yet. "
                    + "Please pick some date in the past\n";
        }
    }

    /**
     * Task5.a: Используя локаль для Канады, Германии, Пакистана и Вьетнама вывести текущую дату в
     * полном формате используя Date-Time API Java 8 и возможности Java 7. Java 7 version.
     */
    public void getCurrentDateJVM7() {
        Calendar currentDate = Calendar.getInstance();
        ArrayList<Locale> locales = new ArrayList<Locale>();
        locales.add(Locale.CANADA);
        locales.add(Locale.GERMANY);
        locales.add(new Locale("en", "PK"));//pakistan locale
        locales.add(new Locale("vi", "VN"));//vietnam locale
        System.out.print("\nCurrent dates:\n");
        for (Locale locale : locales) {
            System.out.printf("%s: %s\n", locale.getCountry(),
                    DateFormat.getDateInstance(DateFormat.FULL, locale).
                    format(currentDate.getTime()));
        }

    }

    /**
     * Task5.b Используя локаль для Канады, Германии, Пакистана и Вьетнама вывести текущую дату в
     * полном формате используя Date-Time API Java 8 и возможности Java 7. Java 8 version.
     */
    public void getCurrentDateJVM8() {
        LocalDate currentDate = LocalDate.now();
        ArrayList<Locale> locales = new ArrayList<>();
        locales.add(Locale.CANADA);
        locales.add(Locale.GERMANY);
        locales.add(new Locale("en", "PK"));//pakistan locale
        locales.add(new Locale("vi", "VN"));//vietnam locale
        System.out.print("\nCurrent dates:\n");
        for (Locale locale : locales) {
            System.out.printf("%s: %s\n", locale.getCountry(),
                    currentDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                            .withLocale(locale)));
        }

    }

}
