package com.nixsolutions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateCollections {

    public static void main(String[] argc) {

        //DateCollections.daysInEachMonth(2015);

       //DateCollections.mondaysInMonth(2016, 9);


        /*String d = "13/01/2017";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = sdf.parse(d);
            System.out.println(DateCollections.isFriday13(date));
            System.out.println(DateCollections.timeGone(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        DateCollections.printCurrentDateForSomeLocal();





    }
}
