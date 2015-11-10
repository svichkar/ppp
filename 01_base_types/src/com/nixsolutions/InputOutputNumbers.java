package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by konstantin on 09.11.2015.
 */
public class InputOutputNumbers {

    public static void main(String args[]) {
        String out = "";
        System.out.println("Please input floating number in common OR scientific format.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            out = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Double.parseDouble(out);

            if (out.toLowerCase().contains("e")) {
                System.out.format("Number in common format: %f", Double.valueOf(out));
            } else {
                System.out.format("Number in scientific format: %E", Double.valueOf(out));
            }
        } catch (Exception ex) {
            System.out.println("Input is not number.");
        }
    }
}