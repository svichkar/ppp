package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

import java.time.*;

public class WorkWithDates {

    public static void main(String[] args){

        DateFormat dateFormat;
        Calendar calendar;
        Date date = null;
        boolean dateEntered = false;
        Scanner inputData = new Scanner(System.in);
        do {
            System.out.println("Please, enter the date in format dd-MM-yyyy.");
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String inputWholeDate = inputData.nextLine();
            calendar = Calendar.getInstance();
            calendar.setLenient(false);
            dateFormat.setCalendar(calendar);
            try{
                date = dateFormat.parse(inputWholeDate);
                dateEntered = true;
            }catch (ParseException ex){
                System.out.println("You entered date in invalid format! Press button 'q' to quit or any other to try " +
                        "again");
                String enteredValue = inputData.nextLine();
                if(enteredValue.equals("q"))
                    return;
            }
        } while(!dateEntered);


        System.out.println("\nWe're going to output each month capacity in entered year:");
        outputMonthLength(calendar.get(Calendar.YEAR));

        System.out.println("\nWe will output all mondays for entered month:");
        outputMondays(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

        System.out.println("\nWe're going to check if current date is Friday, thirteen. ");
        isDateFridayThirteen(date);

        System.out.println("\nCheck duration of time between entered and current date: ");
        timeAgoSince(date);

        System.out.println("\n Print current date with diff locale using Java7 and Java8: \n");
        outCurrentDateForDiffLocals();
    }

    //Duration of each month in input year
    public static void outputMonthLength(int year){
        System.out.println("We output count of days for each month of " + year + " year on follow rows:");
        GregorianCalendar calend = new GregorianCalendar();
        for(int monthIndex = 0; monthIndex < 12; monthIndex++){
            calend.set(year, monthIndex, 1);
            SimpleDateFormat format = new SimpleDateFormat("MMMM");
            Date date = calend.getTime();
            String month = format.format(date);
            int maxDate = calend.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println(month + " has " + maxDate + " days");
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

    //Method that check if entered date is Friday, 13n
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

    //Method that output duration between input and current date
    public static String timeAgoSince(Date dateOld){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(new Date());

        Calendar oldDate = Calendar.getInstance();
        oldDate.setTime(dateOld);

        if(currentDate.before(oldDate)){
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

    // Print date using Java 8 and Java 7 in full format for different locals
    public static void outCurrentDateForDiffLocals()
    {
        Locale localePakistan = new Locale("en", "PK"); //we use English because it is one of official pakistan's language
        Locale localeVietnam = new Locale("vi", "VN");

        //using Java 8
        LocalDate locDate = LocalDate.now();
        DateTimeFormatter tf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);

        System.out.println("We will out date for different Locals in next rows using Java 8:\n");
        System.out.println("Canada local: " + locDate.format(tf.withLocale(Locale.CANADA)));
        System.out.println("Germany local: " + locDate.format(tf.withLocale(Locale.GERMANY)));
        System.out.println("Pakistan local: " + locDate.format(tf.withLocale(localePakistan)));
        System.out.println("Vietnam local: " + locDate.format(tf.withLocale(localeVietnam)) + "\n");

        //using Java 7
        Date currentDate = new Date();
        System.out.println("We will out date for different Locals in next rows using Java 7:\n");
        System.out.println("Canada local: " + DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA).format(currentDate));
        System.out.println("Germany local: " + DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY).format(currentDate));
        System.out.println("Pakistan local: " + DateFormat.getDateInstance(DateFormat.FULL, localePakistan).format(currentDate));
        System.out.println("Vietnam local: " + DateFormat.getDateInstance(DateFormat.FULL, localeVietnam).format(currentDate));

    }

}
