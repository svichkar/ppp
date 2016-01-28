import org.joda.time.*;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.*;

/**
 * Created by Lexx on 25.11.2015.
 */
public class MyCalendar {
    /**
     * calculate days of month
     *
     * @param inputYear
     * @return
     * @throws ParseException
     * @throws NoSuchMethodException
     */
    public Map<String, Integer> lengthForEachMonth(String inputYear) throws ParseException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<String, Integer> daysOfMonth = new LinkedHashMap<String, Integer>();
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setLenient(false);
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        currentDate = new SimpleDateFormat("yyyy", Locale.ENGLISH).parse(inputYear);
        calendar.setTime(currentDate);
        for (int i = 1; i < months.length; i++) {
            int days = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            String currMonth = monthDisplay.format(calendar.getTime());
            daysOfMonth.put(currMonth, days);
            calendar.roll(GregorianCalendar.MONTH, 1);
        }
        return daysOfMonth;
    }
    /**
     * calculates the dates of Mondays
     *
     * @param DayOfWeek
     * @return
     * @throws ParseException
     * @throws NoSuchMethodException
     */
    public Map<String, String> listOfDatesThatFallOnMonday(String inputMounthYear, String DayOfWeek) throws ParseException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        Map<String, String> dayMonday = new LinkedHashMap<String, String>();
        Date currentDate = new Date();
        String syntDate = "";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setLenient(false);
        Date inputDate = new SimpleDateFormat("MM.yyyy", Locale.ENGLISH).parse(inputMounthYear);
        calendar.setTime(inputDate);
        String rightMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String rightYear = String.valueOf(calendar.get(Calendar.YEAR));
        Map<String, Integer> monthLong = lengthForEachMonth(rightYear);
        SimpleDateFormat monthParse = new SimpleDateFormat("MM", Locale.ENGLISH);
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM", Locale.ENGLISH); //Formatting for output
        String currMonth = monthDisplay.format(monthParse.parse(rightMonth));
        try {
            for (int i = 1; i <= monthLong.get(currMonth); i++) {
                syntDate = i + "." + rightMonth + "." + rightYear;
                currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(syntDate);
                calendar.setTime(currentDate);
                String day = calendar.getDisplayName(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.LONG, Locale.ENGLISH);
                if (day.equals(DayOfWeek)) dayMonday.put(syntDate, day);
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format");
            e.getStackTrace();
        }
        return dayMonday;
    }
    /**
     * calculates the dates of "Fridays the thirteenth"
     *
     * @param inputDate
     * @param DayOfWeek - not used
     * @return
     * @throws ParseException
     * @throws NoSuchMethodException
     */
    public String dayFridayThirteen(String inputDate, String DayOfWeek) throws ParseException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        checkDate(inputDate);
        Map<String, String> dayFridayT = new LinkedHashMap<String, String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        Date date = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(inputDate);
        calendar.setTime(date);
        String result = "";
        dayFridayT = listOfDatesThatFallOnMonday((calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR), "Friday");
        result = dayFridayT.get((calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR)));
        try {
            if (result == null) return "Not a Friday the thirteenth";
        } catch (NullPointerException e) {
            //Do not doing anything.
        }
        return "This date is Friday thirteenth";
    }
    /**
     * calculate period between two dates
     *
     * @param targetDate
     * @return
     * @throws ParseException
     */
    public String howManyYearsMonthsDaysHavePassedSinceDate(String targetDate) throws ParseException, InstantiationException, IllegalAccessException {
        checkDate(targetDate);
        Calendar calendar = Calendar.getInstance();
        DateTime currDate = DateTime.now();
        calendar.setTime(new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH).parse(targetDate));
        SimpleDateFormat dateConvert = new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH);
        calendar.setLenient(false);
        dateConvert.setLenient(false);
        String tarDate = dateConvert.format(calendar.getTime());
        Period period = new Period(DateTime.parse(tarDate), currDate, PeriodType.yearMonthDay());
        return period.getYears() + " years " + period.getMonths() + " month " + period.getDays() + " days pass from " + targetDate;
    }
    /**
     * @return String with current Time in different Locales
     */
    public String localTimeForCanadaGermanyPakistanVietnam() {
        java.time.LocalDate localDate = java.time.LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String canadaTime = localDate.format(dateTimeFormatter.withLocale(Locale.CANADA));
        String germanyTime = localDate.format(dateTimeFormatter.withLocale(Locale.GERMANY));
        String pakistanTime = localDate.format(dateTimeFormatter.withLocale(new Locale("en", "PK")));
        String vietnamTime = "";
        Locale[] locales = SimpleDateFormat.getAvailableLocales();
        for (Locale currloc : locales) {
            if (currloc.getCountry().equals("VN")) {
                vietnamTime = localDate.format(dateTimeFormatter.withLocale(currloc));
            }
        }
        return "Canada: " + canadaTime + "\n" + "Germany: " + germanyTime + "\n" + "Pakistan: " + pakistanTime + "\n" + "Vietnam: " + vietnamTime;
    }

    private void checkDate(String dateString) throws IllegalAccessException, InstantiationException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        TemporalAccessor ta = dateTimeFormatter.parse(dateString);
    }
}
