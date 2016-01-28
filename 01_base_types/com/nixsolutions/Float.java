// Float.java
package com.nixsolutions;

import java.util.Scanner;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Float {
  public static void main(String[] args) {
    String e = "e";
    while (true) {
      System.out.print("Please enter floating point number: ");
      Scanner scan = new Scanner(System.in);
      String s = scan.next();
      BigDecimal bd = new BigDecimal(s);
      
      if (s.toLowerCase().contains(e)) {
        System.out.println("Received a number in exponential format");
        System.out.println("This number in normal format: " + bd.toPlainString());
      } else {
        System.out.println("Received a number in normal format");
        DecimalFormat fmt = new DecimalFormat("0.00E00");
        String fs = fmt.format(bd.doubleValue());
        System.out.println("This number in exponential format: " + fmt.format(bd.doubleValue()));
      }
    }
  }
  
}