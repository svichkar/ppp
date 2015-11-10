package com.nixsolutions;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by Sergey on 06.11.2015.
 */
public class ExpNum {
    public static void main(String[] args) throws NumberFormatException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the number");
        String temp = in.nextLine();
        double input = Double.parseDouble(temp);
        temp = temp.toLowerCase();
        if (temp.contains("e")) {
            DecimalFormat myFormatter = new DecimalFormat("#");
            System.out.println(myFormatter.format(input));
        } else {
            DecimalFormat myFormatter = new DecimalFormat("0.###E0");
            System.out.println(myFormatter.format(input));
        }
    }
}


