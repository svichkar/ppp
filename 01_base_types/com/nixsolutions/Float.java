// Float.java
package com.nixsolutions;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
* Converts notation of floating point numbers from one type to another.
*/
public class Float {
  /**
  * Asks the user to enter floating point number,
  * converts the input to another type of notation,
  * then prints the result on the screen.
  */
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