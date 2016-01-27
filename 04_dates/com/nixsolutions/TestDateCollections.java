package com.nixsolutions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateCollections {

    public static void main(String[] argc) {
        //test data
        int year = 2016;
        int month = 11; //0-11 need set (as in the java.util.Calendar format)
        String pattern = "dd/MM/yyyy";
        String dateString = "01/12/1980";

        System.out.println("1. Number of days in each month for " + year + " year:");
        DateCollections.daysInEachMonth(year);
        System.out.println();
        System.out.println("2. Days Monday for " + year + " year and " + month + " month: ");
        DateCollections.mondaysInMonth(year, month);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        System.out.println();
        try {
            Date date = sdf.parse(dateString);
            System.out.println("3. Is " + dateString + " friday 13? " + DateCollections.isFriday13(date));
            System.out.println();
            System.out.println("4. How many time gone after " + dateString + "?");
            System.out.println("Java 7: " + DateCollections.timeGoneJava7(date));
            System.out.println("Java 8: " + DateCollections.timeGoneJava8(date));
        } catch (ParseException e) {
            System.out.println("Incorrect format date.");
        }
        System.out.println();
        System.out.println("5. Current date using different locale:");
        DateCollections.printCurrentDateForSomeLocal();
    }

}
