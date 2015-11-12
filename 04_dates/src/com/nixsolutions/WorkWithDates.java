package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WorkWithDates {

    public static void main(String[] args) throws ParseException{
	/*�������� ����� ������� ��� ���������� ���� ������� ����� ������� ������.
�������� ����� ������� ��� ���������� ������ � ���� ������� ������ ��� ������� �������� �� �����������.
�������� ����� ������� ��������� �������� �� ��������� ���� �������� ������������.
�������� ����� ������� ��� ��������� ���� ���������� ������ � ������� �������� ������� ���, �������, ���� ������ � ���� ����. �������� �2 ����, 1 ����� � 23 ����
��������� ������ ��� ������, ��������, ��������� � �������� ������� ������� ���� � ������ ������� ��������� Date-Time API Java 8 � ����������� Java 7
*/
        System.out.println("Please, enter year and we output each month capacity: ");
        Scanner inputData = new Scanner(System.in);
        String inputYear = inputData.nextLine();
        //outputMonthLength(Integer.valueOf(inputYear));

        System.out.println("Please, enter year and month number (range is [1;12]: ");
        inputYear = inputData.nextLine();
        String inputMonth = inputData.nextLine();
        outputMondays(Integer.valueOf(inputYear), Integer.valueOf(inputMonth));

        System.out.println("Please, enter the date in format dd-MM-yyyy. We're going to check if it is Friday, thirteen. ");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String inputWholeDate = inputData.nextLine();
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        dateFormat.setCalendar(calendar);
        Date date = dateFormat.parse(inputWholeDate);
        isDateFridayThirteen(date);
    }

    //�������� ����� ������� ��� ���������� ���� ������� ����� ������� ������.
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

    //�������� ����� ������� ��� ���������� ������ � ���� ������� ������ ��� ������� �������� �� �����������.
    //month number should be in range [1;12]
    public static void outputMondays(int year, int monthNumber){

        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthNumber - 1);
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

    //�������� ����� ������� ��������� �������� �� ��������� ���� �������� ������������.
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

}
