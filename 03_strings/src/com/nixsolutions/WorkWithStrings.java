package com.nixsolutions;

import java.text.ParseException;
import java.util.Scanner;

public class WorkWithStrings {

    // I have 2nd variant
    public static void main(String[] args) {

        System.out.println("Please input some String to reverse it:");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        System.out.println("You enter: \n" + input + "\nReversed string: \n" +customReverse(input) + "\n");

        System.out.println("Next method deleted needed count of specified symbols from start or end of specified line");
        System.out.println("Please eneter one-by-one: your string, count of symbols to delete, symbol for deleting, " +
                "vector of deleting from ('start' or 'end')  ");
        String line = scan.nextLine();
        int countToDel = 0;
        boolean goForward = false;
        do{
            String count = scan.nextLine();
            try{
                countToDel = Integer.parseInt(count);
                goForward = true;
            }catch (NumberFormatException ex){
                System.out.println("You entered invalid count! Please try again. Please use only integer!");
            }
        } while (!goForward);

        char symbolToDel = ' ';
        goForward = false;
        do{
            String symbol = scan.nextLine();
            try{
                if(symbol.toCharArray().length == 1){
                    symbolToDel = symbol.toCharArray()[0];
                    goForward = true;
                }
                else throw new Exception();
            }catch (Exception ex){
                System.out.println("You entered invalid symbol! Please try again. Please enter only one symbol!");
            }
        } while (!goForward);


        goForward = false;
        boolean beginFromStart = true;
        do{
            String vector = scan.nextLine();
            try{
                if(vector.equals("start")){
                    beginFromStart = true;
                    goForward = true;
                }
                else if(vector.equals("end")){
                    beginFromStart = false;
                    goForward = true;
                }
                else throw new Exception();
            }catch (Exception ex){
                System.out.println("You entered invalid value! Please try again. Please use only 'start' or 'end'!");
            }
        } while (!goForward);

        System.out.println("Results of deleting:" + deleteSomeSymbolsFromLine(line, countToDel, symbolToDel, beginFromStart));


        System.out.println("Please input some String to delete dublicated literals and sort it alphabetically it:");
        String delDublAndSort = scan.nextLine();
        delDublicatesAndSorting(delDublAndSort);
    }

    //Custom reverse without StringBuilder, StringBuffer.
    public static String customReverse(String inputString){
        String result = "";
        for(char symb : inputString.toCharArray()) {
            result = symb + result;
        }
        return result;
    }

    //Delete from string 'line' first 'countToDel' symbols 'symbolToDel'. Strst from the end of string if StartFromFirst
    // is false. Delete both literal types: lower and Capital case.
    public static String deleteSomeSymbolsFromLine(String line, int countToDel, char symbolToDel,
                                                   boolean StartFromFirst){
        String workingCopy;
        if(StartFromFirst)
            workingCopy = line;
        else workingCopy = customReverse(line);
        StringBuilder sbOut = new StringBuilder(workingCopy);

        int counter = 0;
        int index = 0;
        for(Character symb : workingCopy.toCharArray()){
            if(Character.toLowerCase(symb) == Character.toLowerCase(symbolToDel)){
                sbOut.deleteCharAt(index);
                counter++;
                if(counter == countToDel)
                    break;
            }
            else index++;
        }
        if(!StartFromFirst)
            sbOut.reverse();
        return sbOut.toString();
    }

    //Split word on literals, delete dublicates(ignore case) and sort in alphabetical order
    //(case isn't ignored - first Capital letters, second lowercase letters)
    public static void delDublicatesAndSorting(String line)
    {
        char[] lineChars = line.toCharArray();
        String result = "" + lineChars[0];
        //int countChecked = 1;
        for(char symb : lineChars){
            if(!result.toLowerCase().contains(String.valueOf(symb).toLowerCase()))
                result+=symb;
        }
        System.out.println("Dublicates were deleted. Results string is: \n" + result);
        char[] resultsArray = result.toCharArray();
        System.out.println("Before sorting: \n" + String.valueOf(resultsArray));
        java.util.Arrays.sort(resultsArray);
        System.out.println("Sorting literal array is: \n" + String.valueOf(resultsArray));
    }
}
