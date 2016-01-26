import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;

import java.time.LocalDate;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

/**
 * Created by Lexx on 25.11.2015.
 */
public class MyCalendar
{
    /**
     * calculate days of month
     * @param inputYear
     * @return
     * @throws ParseException
     * @throws NoSuchMethodException
     */
    public Map<String, Integer> lengthForEachMonth(String inputYear) throws ParseException, NoSuchMethodException {
        Map<String, Integer> daysOfMonth = new LinkedHashMap<String, Integer>();
        String rightYear = inputYear.replaceAll("\\d{2}[.]","").replaceAll("\\D*","");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setLenient(false);
        String syntDate ="";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        SimpleDateFormat monthParse = new SimpleDateFormat("dd.MM.yyyy",Locale.ENGLISH);
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM",Locale.ENGLISH);
        syntDate = "01.01." + rightYear;
        currentDate = new SimpleDateFormat("dd.MM.yyyy").parse(syntDate);
        calendar.setTime(currentDate);
        for (int i = 1; i < months.length; i++) {
            int days = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            String currMonth = monthDisplay.format(calendar.getTime());
            daysOfMonth.put(currMonth,days);
            calendar.roll(GregorianCalendar.MONTH, 1);
        }
        return daysOfMonth;
    }
    /**
     * calculates the dates of Mondays
     * @param inputMonth
     * @param inputYear
     * @param DayOfWeek
     * @return
     * @throws ParseException
     * @throws NoSuchMethodException
     */
    public Map<String, String> listOfDatesThatFallOnMonday(String inputMonth,String inputYear,String DayOfWeek) throws ParseException, NoSuchMethodException {
        Map<String, String> dayMonday = new LinkedHashMap<String, String>();
        String rightYear = inputYear.replaceAll("\\d{2}[.]","").replaceAll("\\D*","");
        String rightMonth = inputMonth.replaceAll("\\d{4}[.]","").replaceAll("\\D*","");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setLenient(false);
        String syntDate ="";
        Map<String, Integer> monthLong = lengthForEachMonth(rightYear);
        SimpleDateFormat monthParse = new SimpleDateFormat("MM",Locale.ENGLISH);
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM",Locale.ENGLISH);
        String currMonth = monthDisplay.format(monthParse.parse(rightMonth));
        try {
            for (int i = 1; i <= monthLong.get(currMonth); i++) {
                syntDate = i+"."+rightMonth+"."+rightYear;
                currentDate = new SimpleDateFormat("dd.MM.yyyy").parse(syntDate);
                calendar.setTime(currentDate);
                String day = calendar.getDisplayName(GregorianCalendar.DAY_OF_WEEK,GregorianCalendar.LONG,Locale.ENGLISH);
                if(day.equals(DayOfWeek)) dayMonday.put(syntDate,day);
            }
        }
        catch (ParseException e)
        {
            e.getStackTrace();
        }
        return dayMonday;
    }

    /**
     * calculates the dates of "Fridays the thirteenth"
     * @param inputDate
     * @param DayOfWeek - not used
     * @return
     * @throws ParseException
     * @throws NoSuchMethodException
     */
    public String dayFridayThirteen(String inputDate,String DayOfWeek) throws ParseException, NoSuchMethodException {
        Map<String, String> dayFridayT = new LinkedHashMap<String, String>();
        String rightDate = inputDate.replaceAll("\\s", ".");
        String[] dayMonthYear = rightDate.split("\\.");
        String result="";
        dayFridayT = listOfDatesThatFallOnMonday(dayMonthYear[1], dayMonthYear[2], "Friday");
        result = dayFridayT.get(inputDate);
        try{
            if(result.equals("null")) result = "Not a Friday the thirteenth";
        }
        catch (NullPointerException e)
        {
            e.getStackTrace();
        }
        return  "Yes";
    }
    /**
     * calculate period between two dates
     * @param targetDate
     * @return
     * @throws ParseException
     */
    public String howManyYearsMonthsDaysHavePassedSinceDate(String targetDate) throws ParseException {
        String rightDate = targetDate.replaceAll("\\s", ".");
        String[] dayMonthYear = rightDate.split("\\.");
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        Date farDate = new SimpleDateFormat("dd.MM.yyyy").parse(targetDate);
        calendar.setTime(farDate);
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(Integer.parseInt(dayMonthYear[2]),Integer.parseInt(dayMonthYear[1]),Integer.parseInt(dayMonthYear[0]));
        Period p = Period.between(birthday, today);
        return p.getYears()+" years "+p.getMonths()+" month "+p.getDays()+" days";
    }

    /**
     * 
     * @return
     */
    public String localTimeForCanadaGermanyPakistanVietnam()
    {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String canadaTime = localDate.format(dateTimeFormatter.withLocale(Locale.CANADA));
        String germanyTime = localDate.format(dateTimeFormatter.withLocale(Locale.GERMANY));
        String pakistanTime = localDate.format(dateTimeFormatter.withLocale(Locale.ENGLISH));
        String vietnamTime = localDate.format(dateTimeFormatter.withLocale(Locale.ENGLISH));
        return "Canada: "+canadaTime+"\n"+"Germany: "+germanyTime;
    }
}
