import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by svichkar on 11/10/2015.
 */
public class DateMethods {

    public static final int N = 12;

    public static void main(String args[]) {

        getMonthSizesInYear(2016);
        getMondaysForMonthAndYear(2015, Calendar.DECEMBER);
        System.out.println(String.format("%s", isFridayThirteen(LocalDate.parse("2015-11-13"))));
        System.out.println(getTimePeriodFromNowToDate(LocalDate.parse("2014-11-10")));
        getDataInDiffTimeZones();
    }

    private static void getMonthSizesInYear(int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);

        for (int i = 0; i < N; i++) {
            calendar.set(calendar.MONTH, i);
            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.format("%s: %d.%n", month, days);
        }
    }

    private static void getMondaysForMonthAndYear(int year, int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= days; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                System.out.println(date);
            }
        }
    }

    private static boolean isFridayThirteen(LocalDate date) {

        boolean result = false;

        if (date.getDayOfWeek() == DayOfWeek.FRIDAY && date.getDayOfMonth() == 13) {
            result = true;
        }
        return result;
    }

    public static String getTimePeriodFromNowToDate(LocalDate date) {
        LocalDate dateNow = LocalDate.now();
        Period period = Period.between(date, dateNow);
        String result = String.format("%s years %s months %s days between now and %s.%n", period.getYears(), period.getMonths(), period.getDays(), date);
        return result;
    }

    public static void getDataInDiffTimeZones() {
        Date dateJava7 = new Date();
        LocalDate dateJava8 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);

        String canadaLocalTimeJava7 = DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA).format(dateJava7);
        String germanyLocalTimeJava7 = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY).format(dateJava7);

        Locale pakistan = new Locale("pa", "PK");
        String pakistanLocalTimeJava7 = DateFormat.getDateInstance(DateFormat.FULL, pakistan).format(dateJava7);

        Locale vietnam = new Locale("vi", "VN");
        String vietnamLocalTimeJava7 = DateFormat.getDateInstance(DateFormat.FULL, vietnam).format(dateJava7);

        String canadaLocalTimeJava8 = dateJava8.format(formatter.withLocale(Locale.CANADA));
        String germanyLocalTimeJava8 = dateJava8.format(formatter.withLocale(Locale.GERMANY));
        String pakistanLocalTimeJava8 = dateJava8.format(formatter.withLocale(pakistan));
        String vietnamLocalTimeJava8 = dateJava8.format(formatter.withLocale(vietnam));

        System.out.println(String.format("Local time using Java 7: %nCanada: %s%nGermany: %s%nPakistan: %s%nVietnam: %s%n",
                canadaLocalTimeJava7, germanyLocalTimeJava7, pakistanLocalTimeJava7, vietnamLocalTimeJava7));

        System.out.println(String.format("Local time using Java 8: %nCanada: %s%nGermany: %s%nPakistan: %s%nVietnam: %s%n",
                canadaLocalTimeJava8, germanyLocalTimeJava8, pakistanLocalTimeJava8, vietnamLocalTimeJava8));
    }
}
