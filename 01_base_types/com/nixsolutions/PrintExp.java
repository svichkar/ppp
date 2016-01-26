package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrintExp {

    public static void main (String[] argc) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                System.out.println("Enter number in double or exponential format: ");
                String input = br.readLine();
                input = input.replace(',', '.');
                try {
                    if (input.length() > 0 && (input.contains("e") || input.contains("E"))) {
                        //if input number in exp form
                        double d = Double.parseDouble(input);
                        System.out.printf("Normal form: %.8f\n", d);
                    } else {
                        //if input number in normal form
                        double d = Double.parseDouble(input);
                        System.out.println("Exponential form: " + d);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input value is not a number.");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
