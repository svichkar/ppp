package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pantiukhin on 1/25/2016.
 * Subject: working with basic data types
 *
 */
public class ExponentialNumbers {
    public static void main(String[] args) {
        ExponentialNumbers enumbers = new ExponentialNumbers();
        enumbers.convertNumbers();
    }

    public void convertNumbers() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String initialVal = "";
        System.out.println("Enter a number with a floating point: ");
        try {
            initialVal = reader.readLine();
            if (initialVal.toLowerCase().contains("e"))
                System.out.printf("Converted number: %f", Double.parseDouble(initialVal));
            else
                System.out.printf("Converted number: %e", Double.parseDouble(initialVal));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
