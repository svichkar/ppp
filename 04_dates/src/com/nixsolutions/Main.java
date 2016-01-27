import java.io.UnsupportedEncodingException;
import java.text.ParseException;

/**
 * Created by Lexx on 16.11.2015.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyCalendar myCalendar = new MyCalendar();
        try {
            System.out.println(myCalendar.lengthForEachMonth("25.11.2016"));
            System.out.println(myCalendar.listOfDatesThatFallOnMonday("10.2016", "Monday"));
            System.out.println(myCalendar.dayFridayThirteen("13.03.2015", "Friday"));
            System.out.println(myCalendar.howManyYearsMonthsDaysHavePassedSinceDate("15.03.2011"));
            System.out.println(myCalendar.localTimeForCanadaGermanyPakistanVietnam());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

