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
<<<<<<< HEAD:01_base_types/com/nixsolutions/ExpNum.java
        if (temp.contains("e") || temp.contains("E")) {
=======
        temp=temp.toLowerCase();
        if (temp.contains("e")) {
>>>>>>> a78926afbaf90b9dd0857c49a71d9d11c73eb9e5:01_base_types/com/nixsolutions/ExpNum.java
            DecimalFormat myFormatter = new DecimalFormat("#");
            System.out.println(myFormatter.format(input));
        } else {
            DecimalFormat myFormatter = new DecimalFormat("0.###E0");
            System.out.println(myFormatter.format(input));
        }
    }
<<<<<<< HEAD:01_base_types/com/nixsolutions/ExpNum.java
}
=======
}
>>>>>>> a78926afbaf90b9dd0857c49a71d9d11c73eb9e5:01_base_types/com/nixsolutions/ExpNum.java
