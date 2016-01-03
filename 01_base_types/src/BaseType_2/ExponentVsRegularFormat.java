package BaseType_2;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.util.Scanner;

public class ExponentVsRegularFormat {

    public static void main(String[] args) {
        Scanner lineScanner = new Scanner(System.in);
        boolean quit = false;

        do{
            System.out.println("Please enter float in exponent or regular format");
            String input = lineScanner.nextLine();
            try{
                if(input.toLowerCase().contains("e")){
                    System.out.println("You enter next symbols: " + input);
                    double number = Double.parseDouble(input);
                    System.out.println(String.format("Regular view is: %.20f", number));
                    //System.out.println("Regular view is: " + Double.valueOf(input));
                }
                else {
                    System.out.println("You enter next symbols: " + input);
                    double number = Double.parseDouble(input);
                    System.out.println(String.format("Exponent view is: %e", number));

                }
            } catch (Exception ex){
                System.out.println("You enter invalid number. There should be exponent or float number!");
            }

            System.out.println("Please enter 'q' to quit or any other key to repeat.");
            input = lineScanner.nextLine();
            quit = input.equals("q");
        }
        while(!quit);
    }
}
