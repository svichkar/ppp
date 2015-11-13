package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WorkWithDates {

    public static void main(String[] args) throws ParseException{
	/*Ќаписать метод который дл€ указанной даты возвращает строку в которой написано сколько лет, мес€цев, дней прошло с этой даты. Ќапример У2 года, 1 мес€ц и 23 дн€Ф
»спользу€ локаль дл€  анады, √ермании, ѕакистана и ¬ьетнама вывести текущую дату в полном формате использу€ Date-Time API Java 8 и возможности Java 7
*/      System.out.println("Please, enter the date in format dd-MM-yyyy.");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Scanner inputData = new Scanner(System.in);
        String inputWholeDate = inputData.nextLine();
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        dateFormat.setCalendar(calendar);
        Date date = dateFormat.parse(inputWholeDate);


        System.out.println("\nWe're going to output each month capacity in entered year:");
        outputMonthLength(calendar.get(Calendar.YEAR));

        System.out.println("\nWe will output all mondays for entered month:");
        outputMondays(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

        System.out.println("\nWe're going to check if current date is Friday, thirteen. ");
        isDateFridayThirteen(date);

        System.out.println("\nCheck duration of time between entered and current date: ");
        timeAgoSince(date);
    }

    //Ќаписать метод который дл€ указанного года выводит длину каждого мес€ца.
    public static void outputMonthLength(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(Calendar.YEAR, year);
        System.out.println("We output count of days for each month of " + year + " year on follow rows:");
        for(int monthIndex = 0; monthIndex < 12; monthIndex++){
            calendar.set(Calendar.MONTH, monthIndex);
            System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " has "
                    + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + " days");
        }
    }

    //month index should be in range [0;11]
    public static void outputMondays(int year, int monthIndex){

        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthIndex);
        String out = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " contains next Mondays dates: ";
        for (int day = 1; day <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); day++) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                out = out + "\n" + String.valueOf(day);
                //if we found monday we can miss next 6 days before next monday
                day+=6;
            }
        }
        System.out.println(out);
    }

    public static boolean isDateFridayThirteen(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        boolean isFr13 = calendar.get(Calendar.DAY_OF_MONTH) == 13 &&
                calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).equals("Friday");
        if(isFr13)
            System.out.println("Current date is Friday, 13 !");
        else
            System.out.println("Current date isn't Friday, 13!");
        return isFr13;
    }


    //Ќаписать метод который дл€ указанной даты возвращает строку в которой написано сколько лет, мес€цев, дней прошло с этой даты. Ќапример У2 года, 1 мес€ц и 23 дн€Ф

    public static String timeAgoSince(Date dateOld)
    {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(new Date());

        Calendar oldDate = Calendar.getInstance();
        oldDate.setTime(dateOld);

        if(currentDate.before(oldDate))
        {
            System.out.println("You enter date from future!");
            return "You enter date from future!";
        }

        int deltaDay = currentDate.get( Calendar.DAY_OF_MONTH ) - oldDate.get( Calendar.DAY_OF_MONTH );
        int deltaMonth = currentDate.get( Calendar.MONTH ) - oldDate.get( Calendar.MONTH );
        int deltaYear = currentDate.get( Calendar.YEAR ) - oldDate.get( Calendar.YEAR );

        if ( deltaDay < 0 ) {
            deltaDay += new GregorianCalendar(currentDate.get( Calendar.YEAR ), currentDate.get( Calendar.DAY_OF_MONTH ) - 1, 0)
                    .getActualMaximum((Calendar.DAY_OF_MONTH));
            deltaMonth--;
        }

        if ( deltaMonth < 0 ) {
            deltaMonth += 12;
            deltaYear--;
        }
        String differencePhrase = String.format("%d year(s), %d month(s), %d day(s)", deltaYear, deltaMonth, deltaDay);
        System.out.println(differencePhrase +" is passed since old  date!");
        return differencePhrase;
    }
}
